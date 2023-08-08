package com.teamProject.ezmeal.service;

import com.teamProject.ezmeal.dao.ProductImgDao;
import com.teamProject.ezmeal.domain.ProductImgDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductImgService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Autowired
    ProductImgDao productImgDao;

    /*상품 코드 입력해서 모든 이미지 List로 반환하기 */
    public List<ProductImgDto> getImgFromProdCd (Long prodCd) throws SQLException {
        return productImgDao.selectProdCdImgAll(prodCd);
    }

    /* 상품코드로 이미지 찾는데 특정 타입 이미지가 필요할 때 */
    public ProductImgDto getTypImgFromProdCd (Long prodCd, String typ) throws SQLException {
        return productImgDao.selectProdCdTypImg(prodCd, typ);
    }


    /* 카테고리로 검색한 상품리스트의 대표 이미지 리스트 (와일드카드 사용) */
    public List<ProductImgDto> getImgFromCateCd (String cate_cd) throws SQLException {
        String like_cate_cd = cate_cd+"%";
        return productImgDao.selectCateCdImgTyp(like_cate_cd);
    }


    /* 위의  List<ProductImgDto> -> 변환 -> Map<String(Prod_cd), ProductImgDto> */
    public Map<Long, ProductImgDto> cateCdImgListConvertToMap(String cate_cd) throws SQLException {
        List<ProductImgDto> selectCateCdImgList = getImgFromCateCd(cate_cd);
        Map<Long, ProductImgDto> selectCateCdImgMap
                = selectCateCdImgList.stream().collect(Collectors.toMap(ProductImgDto->ProductImgDto.getProd_cd(), ProductImgDto->ProductImgDto));
        return  selectCateCdImgMap;
    }


    /*한 상품에 대한 전체 이미지 받기 List*/
    public List<ProductImgDto> getAllImgFromProdCd(Long prod_cd) throws SQLException {
        List<ProductImgDto> selectProdCdImgList = productImgDao.selectProdCdImgAll(prod_cd);
        return selectProdCdImgList;
    }

    /*위에서 받은  list 타입별 map으로 변환하기 (대표,대표이미지)(메인,메인이미지),(상세, 상세이미지)*/
    public Map<String,String> getAllImgFromProdCdConvertToMap(Long prod_cd) throws SQLException {
        List<ProductImgDto> selectProdCdImgList = productImgDao.selectProdCdImgAll(prod_cd);
        Map<String,String> typeAndUrlMap =  selectProdCdImgList.stream().collect(Collectors.toMap(ProductImgDto->ProductImgDto.getTyp(),ProductImgDto->ProductImgDto.getUrl()));
        return typeAndUrlMap;
    }

    /*현재 판매하는 상품의 모든 대표 이미지 (상품코드 , 이미지 객체)*/
    public Map<Long, ProductImgDto> getAllRecentTypImgListConvertToMap() throws SQLException {
        List<ProductImgDto> imgList = productImgDao.selectAllImgTypRepresent();
        Map<Long, ProductImgDto> selectCateCdImgMap
                = imgList.stream().collect(Collectors.toMap(ProductImgDto->ProductImgDto.getProd_cd(), ProductImgDto->ProductImgDto));
        return  selectCateCdImgMap;
    }



    /*--------------상품 이미지 등록---------------*/

    /*리스트 등록  (대표, 메인 4-5, 상세)*/


    /*이미지 업로드(저장) -> 반환타입 이미지 DTO, -> 받은 이미지 dto 필요한 값 넣어주기 완성품 반환하기->
    *Insert 하기  각각 메서드로 만들기*/

    /*1. 이미지 업로드 (1개짜리)*/
    public ProductImgDto uploadImgAndMakeProdImg(MultipartFile uploadImg, Long prod_cd, String typ) throws IOException {
        String uploadFolderPath = uploadDir;
        System.out.println("uploadFolder 주소 = " + uploadFolderPath);

        //파일이름 꺼내오기
        String uploadFileName = uploadImg.getOriginalFilename();
        System.out.println("uploadFileName = " + uploadFileName);
        //확장자 꺼내기
        String extns = uploadFileName.substring(uploadFileName.lastIndexOf(".") + 1);
        System.out.println("확장자: " + extns);

        //파일이름 정제? 왜하는지 모르겠지만 일단 함
        uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\")+1);
        System.out.println("only file Name: " + uploadFileName);

        //파일 이름 저장 약속 : 오늘날짜_현재시간_prod_파일이름.확장자
        String today = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String now = new SimpleDateFormat("HHmmssSSS").format(new Date());
        uploadFileName = today + "_" + now + "_p_" + uploadFileName;

        //가로세로 사이즈
        BufferedImage image = ImageIO.read(uploadImg.getInputStream());
        int width = image.getWidth();
        int height = image.getHeight();

        //디테일 값
        String detail = "";
        switch (typ){
            case "대표":
                detail= "상품카테고리 대표이미지";
                break;
            case "메인":
                detail= "상품상세 메인 이미지";
                break;
            case "상세":
                detail= "상품상세 상세설명 이미지";
                break;
        }

        //이미지 객체 생성하기  (우리 상품 이미지 이름은 확장자 포함하고 있음 )
        ProductImgDto productImgDto = new ProductImgDto( prod_cd , typ , detail, uploadFileName,  height,  width, extns, null );


        try{
            //파일 저장하기 new File(파일저장할 폴더 주소, 저장할 대상)
            File saveFile = new File(uploadFolderPath, uploadFileName);
            System.out.println("new file Name: " + uploadFileName);

            //업로드 되는 파일 저장하는 메서드 .transferTo( 매개변수로 File객체 필요함 )
            uploadImg.transferTo(saveFile);

    /*      //썸네일 생성하는 코드 (작성만 해두고 나중에 사용할 듯)
            if(checkImageType(saveFile)){
            //썸네일 이미지 파일 이름 :  s_ + 파일이름
            FileOutputStream thumbnail = new FileOutputStream(new File(uploadFolderPath, "s_"+uploadFileName));
            //썸네일이미지 생성
            Thumbnailator.creatThumnail(multipartFile.getInputStream(), thumbnail, 100, 100);
            thumbnail.close();
            }*/

            } catch (IOException e) {
                    System.out.println("IOException입니다: "+e.getMessage());
            } catch (Exception e){
                    System.out.println("Exception입니다: "+e.getMessage());
        }

        return productImgDto;

    }


    /*이미지 DTO INSERT 메서드*/
    public int registProductImgInfoOne(ProductImgDto productImgDto) throws SQLException {
        int registResult = productImgDao.insertImgOne(productImgDto);
        return registResult;
    }


    //MultipartFile 이미지배열 ing 폴더에 업로드 -> insert imgDto List
    public int uploadMultipleImages(MultipartFile[] uploadProdImg, Long prod_cd) throws IOException, SQLException {
        int registResult = 0;
            //이미지파일 배열 길이
        int length = uploadProdImg.length;
            //타입은 배열 순서로 정함
        String typ = "";

        for (int i = 0; i < length; i++) {

            if (i == 0) {
                System.out.println("대표 i = " + i);
                typ = "대표";
            } else if ((0 < i) && (i < length - 1)) {
                System.out.println("메인 i = " + i);
                typ = "메인";
            } else {
                System.out.println("상세 i = " + i);
                typ = "상세";
            }
            ProductImgDto productImgDto = uploadImgAndMakeProdImg(uploadProdImg[i],prod_cd, typ);
            System.out.println("i번째 파일로 만든 dto: "+ productImgDto.toString());
            registResult += registProductImgInfoOne(productImgDto);

        }//for문 끝

        System.out.println("uploadMultipleImages메서드의 이미지 등록개수"+registResult);
        return registResult;
    }


    //MultipartFile 이미지 하나 저장 하는 메서드
    public int uploadMultipleImageOne(MultipartFile uploadImg, Long prod_cd, String typ) throws IOException, SQLException {
        int registResult = 0;

        //이미지 저장 및 객체 생성 하는 uploadImgAndMakeProdImg(MultipartFile uploadImg, Long prod_cd, String typ) 사용
        ProductImgDto productImgDto = uploadImgAndMakeProdImg(uploadImg, prod_cd, typ );
        registResult += registProductImgInfoOne(productImgDto);

        return registResult;
    }


















}
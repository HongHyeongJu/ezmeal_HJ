<%-- 한글 깨짐을 막아줌 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link rel="stylesheet" href="/css/components/prod_review_modal.css"/>

        <div class="modal_body">
            <h2>고객님의 리뷰를 작성해주세요!</h2>
            <%-- 넘겨야하는 값 : ord_id, prod_cd, [mbr_id -> 이걸로 writer], title, stmt, star --%>
            <form method="POST" action="/modal/review/${orderId}">
                <input class="prod-review-modal__order-detail-id" name="ord_prod_dtl_id" hidden />
                <input class="prod-review-modal__prod-cd" name="prod_cd" hidden />

                <label for="rating" class="prod-review-modal__rate">평점</label>
                <select id="rating" name="star" class="prod-review-modal__rate" required>
                    <option value="5">★★★★★</option>
                    <option value="4">★★★★☆</option>
                    <option value="3">★★★☆☆</option>
                    <option value="2">★★☆☆☆</option>
                    <option value="1">★☆☆☆☆</option>
                </select>

                <label for="review_title" class="prod-review-modal__title">리뷰 제목</label>
                <input type="text" id="review_title" name="title" required>

                <label for="review_img" class="prod-review-modal__img">이미지 첨부파일</label>
                <label class="custom-file-input" for="review_img">파일 선택</label>
                <input type="file" id="review_img" accept="image/*">

                <label for="review_content"  class="prod-review-modal__content">리뷰 내용</label>
                <textarea id="review_content" name="stmt" rows="6" required></textarea>

                <button type="submit"  class="prod-review-modal__submit">리뷰 제출</button>
                <button type="button" class="prod_review_cancel">취소</button>
            </form>
        </div>
    <!-- modal main contents-->

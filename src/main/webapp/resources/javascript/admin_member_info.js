$('#button').click(function (){
    const searchOption = $("#searchOption").val(); // select 값
    const searchValue = $("input[name='searchValue']").val();   // input태그 값
    console.log(searchOption);
    console.log(searchValue);
    $.ajax({
        url: '/admin/members',
        type: 'GET',
        data: {option:searchOption, value: searchValue},
        dataType: 'json',
        success: function(memberList) {
            console.log(memberList);
            viewMemberList(memberList);
        },
        error: function(error) {
            console.error('Error:', error);
        }
    });
});

function viewMemberList(memberList){
    const tableBody = document.getElementById('memberTableBody');
    tableBody.innerHTML = '';   // 기존 테이블 내용 초기화

    memberList.forEach(function (member) {
        const tr = document.createElement('tr');

        // member.gender === 'm' ? '남자' : '여자';
        const genderText = member.gender === 'm' ? '남자' : '여자';
        console.log(genderText);

        tr.innerHTML = `
                <td>${member.in_dtm}</td>
                <td>${member.name}</td>
                <td>${member.lgin_id}</td>
                <td>${member.mbr_grd_name}</td>
                <td>${member.phone}</td>
                <td>${genderText}</td>
                <td>${member.birth}</td>
      `;
        tableBody.appendChild(tr);
    });
}


// function displayMembersTable(membersList) {
//     const tableBody = $('#membersTableBody'); // 테이블의 tbody 요소 선택
//
//     // 기존 테이블 내용 초기화
//     tableBody.empty();
//
//     // membersList를 순회하면서 테이블 행(tr) 생성하여 데이터 채우기
//     $.each(membersList, function(index, member) {
//         // 날짜 포맷 변환
//         <%--const inDate = new Date(member.in_dtm);--%>
//         <%--const formattedInDate = `${inDate.getFullYear()}-${(inDate.getMonth() + 1).toString().padStart(2, '0')}-${inDate.getDate().toString().padStart(2, '0')}`;--%>
//
//         // 테이블 셀(td) 생성 및 데이터 채우기
//         const tr = $('<tr>');
//         tr.append(`<td>${formattedInDate}</td>`);
//         tr.append(`<td>${member.name}</td>`);
//         tr.append(`<td>${member.lgin_id}</td>`);
//         tr.append(`<td>${member.mbr_grd_name}</td>`);
//         tr.append(`<td>${member.phone}</td>`);
//         tr.append(`<td>${member.gender == 'm' ? '남자' : '여자'}</td>`);
//         tr.append(`<td>${member.birth}</td>`);
//
//         // 테이블의 tbody에 행(tr) 추가
//         tableBody.append(tr);
//     });
// }
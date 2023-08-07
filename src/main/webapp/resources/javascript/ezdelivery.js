let list = document.querySelectorAll(".list");
let itemBox = document.querySelectorAll(".itemBox");

// 초기화 함수
function initialize() {
    for (let k = 0; k < itemBox.length; k++) {
        itemBox[k].classList.add("hide");

        // "product" 대신 "direct"로 변경
        if (itemBox[k].getAttribute("data-item") === "direct") {
            itemBox[k].classList.remove("hide");
            itemBox[k].classList.add("active");
        }
    }
    // '산지직송'에 대한 리스트 항목에 'active' 클래스 추가
    for (let i = 0; i < list.length; i++) {
        if (list[i].getAttribute("data-filter") === "direct") {
            list[i].classList.add("active");
        }
    }
}

// 초기화 실행
initialize();

for (let i = 0; i < list.length; i++) {
    list[i].addEventListener("click", function () {
        for (let j = 0; j < list.length; j++) {
            list[j].classList.remove("active");
        }
        this.classList.add("active");

        let dataFilter = this.getAttribute("data-filter");

        for (let k = 0; k < itemBox.length; k++) {
            itemBox[k].classList.remove("active");
            itemBox[k].classList.add("hide");

            if (
                itemBox[k].getAttribute("data-item") === dataFilter ||
                dataFilter === "all"
            ) {
                itemBox[k].classList.remove("hide");
                itemBox[k].classList.add("active");
            }
        }
    });
}
// 와 해결했다. 초기화함수를 써야하는거였네 초기화함수를 썼기때문에 처음 베이직단추의 화면에
//밸런스, 플러스의 사진이 보이지 않는다!!!!!! 지피티 지렸다 진짜 . 나중엔 내가 생각..ㅎ...내..가..생..각....ㅎ...
//산지직송에 대한 이미지를 기본적으로 보이게 하려면 data-item이 "direct"인 요소를 찾아 보이게 만들어야 한다.

// start :  배송 가능 지역 지도
document.addEventListener("DOMContentLoaded", function () {
    var seoul = document.getElementById("KR-11");

    seoul.addEventListener("click", function () {
        document.querySelector(".delivery-region-discription #info").innerHTML =
            "<strong>서울특별시</strong><br/><br/> <span class='bold-text'>EZ배송</span><br/> 주문시간 : 오늘 밤 11시 전<br/> 배송시간 : 내일 아침 7시 전<br/><br/> <span class='bold-text'>택배배송</span> <br/>주문시간 : 밤 10시 전<br/> 배송시간 : 다음날 도착<br/><br/>주문시간 : 밤 12시전<br/> 배송시간 : 모레 도착";
    });
});

document.addEventListener("DOMContentLoaded", function () {
    var busan = document.getElementById("KR-26");

    busan.addEventListener("click", function () {
        document.querySelector(".delivery-region-discription #info").innerHTML =
            "<strong>부산광역시</strong><br/><br/> <span class='bold-text'>EZ배송</span><br/> 주문시간 : 오늘 밤 11시 전<br/> 배송시간 : 내일 아침 8시 전<br/><br/> <span class='bold-text'>택배배송</span> <br/>주문시간 : 밤 10시 전<br/> 배송시간 : 다음날 도착<br/><br/>주문시간 : 밤 12시전<br/> 배송시간 : 모레 도착";
    });
});

document.addEventListener("DOMContentLoaded", function () {
    var daegu = document.getElementById("KR-27");

    daegu.addEventListener("click", function () {
        document.querySelector(".delivery-region-discription #info").innerHTML =
            "<strong>대구광역시</strong><br/><br/> <span class='bold-text'>EZ배송</span><br/> 주문시간 : 오늘 밤 11시 전<br/> 배송시간 : 내일 아침 시 전<br/><br/> <span class='bold-text'>택배배송</span> <br/>주문시간 : 밤 10시 전<br/> 배송시간 : 다음날 도착<br/><br/>주문시간 : 밤 12시전<br/> 배송시간 : 모레 도착";
    });
});

document.addEventListener("DOMContentLoaded", function () {
    var incheon = document.getElementById("KR-28");

    incheon.addEventListener("click", function () {
        document.querySelector(".delivery-region-discription #info").innerHTML =
            "<strong>인천광역시</strong><br/><br/> <span class='bold-text'>EZ배송</span><br/> 주문시간 : 오늘 밤 11시 전<br/> 배송시간 : 내일 아침 7시 전<br/><br/> <span class='bold-text'>택배배송</span> <br/>주문시간 : 밤 10시 전<br/> 배송시간 : 다음날 도착<br/><br/>주문시간 : 밤 12시전<br/> 배송시간 : 모레 도착";
    });
});

document.addEventListener("DOMContentLoaded", function () {
    var gwangju = document.getElementById("KR-29");

    gwangju.addEventListener("click", function () {
        document.querySelector(".delivery-region-discription #info").innerHTML =
            "<strong>광주광역시</strong><br/><br/> <span class='bold-text'>EZ배송</span><br/> 배송불가<br/> 추후 서비스 예정입니다.</br><br/> <span class='bold-text'>택배배송</span> <br/>주문시간 : 밤 10시 전<br/> 배송시간 : 다음날 도착<br/><br/>주문시간 : 밤 12시전<br/> 배송시간 : 모레 도착";
    });
});

document.addEventListener("DOMContentLoaded", function () {
    var daejeon = document.getElementById("KR-30");

    daejeon.addEventListener("click", function () {
        document.querySelector(".delivery-region-discription #info").innerHTML =
            "<strong>대전광역시</strong><br/><br/> <span class='bold-text'>EZ배송</span><br/> 주문시간 : 오늘 밤 11시 전<br/> 배송시간 : 내일 아침 7시 전<br/><br/> <span class='bold-text'>택배배송</span> <br/>주문시간 : 밤 10시 전<br/> 배송시간 : 다음날 도착<br/><br/>주문시간 : 밤 12시전<br/> 배송시간 : 모레 도착";
    });
});

document.addEventListener("DOMContentLoaded", function () {
    var ulsan = document.getElementById("KR-31");

    ulsan.addEventListener("click", function () {
        document.querySelector(".delivery-region-discription #info").innerHTML =
            "<strong>울산광역시</strong><br/><br/> <span class='bold-text'>EZ배송</span><br/> 주문시간 : 오늘 밤 11시 전<br/> 배송시간 : 내일 아침 8시 전<br/><br/> <span class='bold-text'>택배배송</span> <br/>주문시간 : 밤 10시 전<br/> 배송시간 : 다음날 도착<br/><br/>주문시간 : 밤 12시전<br/> 배송시간 : 모레 도착";
    });
});

document.addEventListener("DOMContentLoaded", function () {
    var sejong = document.getElementById("KR-36");

    sejong.addEventListener("click", function () {
        document.querySelector(".delivery-region-discription #info").innerHTML =
            "<strong>세종특별자치시</strong><br/><br/> <span class='bold-text'>EZ배송</span><br/> 주문시간 : 오늘 밤 11시 전<br/> 배송시간 : 내일 아침 7시 전<br/><br/> <span class='bold-text'>택배배송</span> <br/>주문시간 : 밤 10시 전<br/> 배송시간 : 다음날 도착<br/><br/>주문시간 : 밤 12시전<br/> 배송시간 : 모레 도착";
    });
});

document.addEventListener("DOMContentLoaded", function () {
    var gyeonggi = document.getElementById("KR-41");

    gyeonggi.addEventListener("click", function () {
        document.querySelector(".delivery-region-discription #info").innerHTML =
            "<strong>경기도</strong><br/><br/> <span class='bold-text'>EZ배송</span><br/> 주문시간 : 오늘 밤 11시 전<br/> 배송시간 : 내일 아침 7시 전<br/><br/> <span class='bold-text'>택배배송</span> <br/>주문시간 : 밤 10시 전<br/> 배송시간 : 다음날 도착<br/><br/>주문시간 : 밤 12시전<br/> 배송시간 : 모레 도착";
    });
});

document.addEventListener("DOMContentLoaded", function () {
    var gangwon = document.getElementById("KR-42");

    gangwon.addEventListener("click", function () {
        document.querySelector(".delivery-region-discription #info").innerHTML =
            "<strong>강원특별자치도</strong><br/><br/> <span class='bold-text'>EZ배송</span><br/> 주문시간 : 오늘 밤 11시 전<br/> 배송시간 : 내일 아침 7시 전<br/><br/> <span class='bold-text'>택배배송</span> <br/>주문시간 : 밤 10시 전<br/> 배송시간 : 다음날 도착<br/><br/>주문시간 : 밤 12시전<br/> 배송시간 : 모레 도착";
    });
});

document.addEventListener("DOMContentLoaded", function () {
    var southChungcheong = document.getElementById("KR-43");

    southChungcheong.addEventListener("click", function () {
        document.querySelector(".delivery-region-discription #info").innerHTML =
            "<strong>충청북도</strong><br/><br/> <span class='bold-text'>EZ배송</span><br/> 주문시간 : 오늘 밤 11시 전<br/> 배송시간 : 내일 아침 7시 전<br/><br/> <span class='bold-text'>택배배송</span> <br/>주문시간 : 밤 10시 전<br/> 배송시간 : 다음날 도착<br/><br/>주문시간 : 밤 12시전<br/> 배송시간 : 모레 도착";
    });
});

document.addEventListener("DOMContentLoaded", function () {
    var NorthChungcheong = document.getElementById("KR-44");

    NorthChungcheong.addEventListener("click", function () {
        document.querySelector(".delivery-region-discription #info").innerHTML =
            "<strong>충청남도</strong><br/><br/> <span class='bold-text'>EZ배송</span><br/> 주문시간 : 오늘 밤 11시 전<br/> 배송시간 : 내일 아침 7시 전<br/><br/> <span class='bold-text'>택배배송</span> <br/>주문시간 : 밤 10시 전<br/> 배송시간 : 다음날 도착<br/><br/>주문시간 : 밤 12시전<br/> 배송시간 : 모레 도착";
    });
});

document.addEventListener("DOMContentLoaded", function () {
    var NorthJeolla = document.getElementById("KR-45");

    NorthJeolla.addEventListener("click", function () {
        document.querySelector(".delivery-region-discription #info").innerHTML =
            "<strong>전라북도</strong><br/><br/> <span class='bold-text'>EZ배송</span><br/> 배송불가<br/> 추후 서비스 예정입니다.</br><br/> <span class='bold-text'>택배배송</span> <br/>주문시간 : 밤 10시 전<br/> 배송시간 : 다음날 도착<br/><br/>주문시간 : 밤 12시전<br/> 배송시간 : 모레 도착";
    });
});

document.addEventListener("DOMContentLoaded", function () {
    var southJeolla = document.getElementById("KR-46");

    southJeolla.addEventListener("click", function () {
        document.querySelector(".delivery-region-discription #info").innerHTML =
            "<strong>전라남도</strong><br/><br/> <span class='bold-text'>EZ배송</span><br/> 배송불가<br/> 추후 서비스 예정입니다.</br><br/> <span class='bold-text'>택배배송</span> <br/>주문시간 : 밤 10시 전<br/> 배송시간 : 다음날 도착<br/><br/>주문시간 : 밤 12시전<br/> 배송시간 : 모레 도착";
    });
});

document.addEventListener("DOMContentLoaded", function () {
    var NorthGyeongsang = document.getElementById("KR-47");

    NorthGyeongsang.addEventListener("click", function () {
        document.querySelector(".delivery-region-discription #info").innerHTML =
            "<strong>경상북도</strong><br/><br/> <span class='bold-text'>EZ배송</span><br/>경상북도 일부 지역 : 양산 </br> 이 외의 지역은 추후 서비스 예정입니다. </br></br> 주문시간 : 오늘 밤 11시 전<br/> 배송시간 : 내일 아침 7시 전<br/><br/> <span class='bold-text'>택배배송</span> <br/>주문시간 : 밤 10시 전<br/> 배송시간 : 다음날 도착<br/><br/>주문시간 : 밤 12시전<br/> 배송시간 : 모레 도착";
    });
});

document.addEventListener("DOMContentLoaded", function () {
    var SouthGyeongsang = document.getElementById("KR-48");

    SouthGyeongsang.addEventListener("click", function () {
        document.querySelector(".delivery-region-discription #info").innerHTML =
            "<strong>경상남도</strong><br/><br/> <span class='bold-text'><span class='bold-text'>EZ배송</span></span><br/> 경상남도 일부 지역 : 김해, 창원 </br> 이 외의 지역은 추후 추후 서비스 예정입니다. </br></br>주문시간 : 오늘 밤 11시 전<br/> 배송시간 : 내일 아침 7시 전<br/><br/> <span class='bold-text'>택배배송</span> <br/>주문시간 : 밤 10시 전<br/> 배송시간 : 다음날 도착<br/><br/>주문시간 : 밤 12시전<br/> 배송시간 : 모레 도착";
    });
});

document.addEventListener("DOMContentLoaded", function () {
    var Jeju = document.getElementById("KR-50");

    Jeju.addEventListener("click", function () {
        document.querySelector(".delivery-region-discription #info").innerHTML =
            "<strong>제주지역</strong><br/><br/> <span class='bold-text'><span class='bold-text'>EZ배송</span></span><br/> 배송불가<br/> 추후 서비스 예정입니다.</br><br/> <span class='bold-text'>택배배송</span> <br/>주문시간 : 밤 10시 전<br/> 배송시간 : 다음날 도착<br/><br/>주문시간 : 밤 12시전<br/> 배송시간 : 모레 도착";
    });
});

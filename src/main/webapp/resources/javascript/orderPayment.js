let htmlString = '';
const renderHTMLFrom = function (allOrderPaymentList) {
    allOrderPaymentList.forEach((allOrderPayment) =>{
        htmlString += `<div class="order-history__products-header">
                <h4 class="order-history__products-header__title">
                    ${allOrderPayment.date_time}
                </h4>
                <a
                        href="/order/detail/{path-variable}"
                        class="order-history__products-header__detail-link"
                >
                    주문내역 상세보기 >
                </a>
            </div>
            <!-- 주문 내역 header 시작 -->

            <!-- 주문 내역 detail 시작 -->
            <div class="order-history__products-main">
                <div class="order-history__products-main__products">
                    <img src="img/goods.png" class="order-history__products-img"/>
                    <div class=order-history__products-definition">
                        <dl class="order-history__products-definition__list">
                            <dt class="order-history__products-definition__title">상품명</dt>
                            <dd class="order-history__products-definition__detail">
                               ${allOrderPayment.prod_smry} // todo
                            </dd>
                        </dl>
                        <dl class="order-history__products-definition__list">
                            <dt class="order-history__products-definition__title">
                                주문번호
                            </dt>
                            <dd
                                    class="order-history__products-definition__detail order-history"
                            >
                                2307120460177
                            </dd>
                        </dl>
                        <dl class="order-history__products-definition__list">
                            <dt class="order-history__products-definition__title">
                                결제방법
                            </dt>
                            <dd class="order-history__products-definition__detail">
                                카카오페이
                            </dd>
                        </dl>
                        <dl class="order-history__products-definition__list">
                            <dt class="order-history__products-definition__title">
                                결제금액
                            </dt>
                            <dd class="order-history__products-definition__detail">0원</dd>
                        </dl>
                    </div>
                    <!-- order-history__products-definition 끝 -->
                </div>
                <!-- order-history__products-main__products 끝 -->
                <div class="order-history__products-main__status">
                    <span class="order-history__products__status-span">결제완료</span>
                    <div class="order-history__products__status-function">
                        전체취소(기능div)
                    </div>
                </div>
                <!-- order-history__products-main__status 끝 -->
            </div>`;
    })
}
function getOrderPaymentData() {
    fetch('/orderPayment/dynamicData', {
        method: 'GET',
    })
        .then(response => response.json())
        .then(data => {
            console.log(data);
            renderHTMLFrom(data);
        })
        .catch(error => {
            console.error('Error:', error);
        });
}

document.addEventListener('DOMContentLoaded', getOrderPaymentData); // html 문서 load 된 후 실행되는 js 함수
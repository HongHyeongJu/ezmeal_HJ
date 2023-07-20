$.ajax({
    url: '/product/catelist',  // Your API endpoint
    method: 'GET',
    data: {
        'sortKeyword': sortKeyword,
        'cate_cd': cate_cd
    },
    success: function(data) {
        // Assuming the data is an array of products
        var html = '';
        for (var i = 0; i < data.prodList.length; i++) {
            // Build your HTML structure here based on your data structure
            html += '<li>' + data.prodList[i].name + '</li>';
        }
        // Replace the current product list with the new one
        $('.prod_list_ul').html(html);
    },
    error: function(error) {
        // Handle error here
    }
});

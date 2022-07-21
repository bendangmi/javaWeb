window.onload=function (){
    // 当页面加载完成后，我们需要绑定各种事件
    // 根据id获取到表格
    var band = document.getElementById(`father_table`);
    var rows = band.rows;

    for (let i = 0; i < rows.length; i++) {
        var tr = rows[i];

        // 1.绑定鼠标悬浮设置背景颜色事件
        tr.onmouseover=showByColor;

        // 2.清除样式
        tr.onmouseout=clearByColor;

        // 获取tr这一行的所有单元格
        let cells = tr.cells;
        let priceID = cells[1];

        // 3.绑定鼠标悬浮在单价单元格变手势的事件
        priceID.onmouseover=showHand;

        // 4.绑定鼠标点击单元格的事件
        priceID.onclick=editPrice;

    }
}

function editPrice(){
    var priceID = event.srcElement;

    // innerHTML 表示设置当前节点的内部HTML
    priceID.innerHTML="<input type='text' size='4'/> "
}

function showHand(){
    var td = event.srcElement ;
    //cursor : 光标
    td.style.cursor="hand";
}



// 单元格变色
function showByColor() {
    // event: 当前发生的事件
    // event.srcElement:事件源
    // alert(event.srcElement.tagName);
    console.log(event);
    console.log(event.srcElement);
    console.log(event.srcElement.tagName == "TD");


    var td = event.srcElement;

    // td.parentElement 表示获取td的父元素 -》 TR
    var tr = td.parentElement;

    // 如果想要通过js代码设置某节点的样式，则需要加上 .style
    tr.style.backgroundColor = "red";

    // tr.cells表示获取这个tr中的所以单元格
    var tds = tr.cells;
    for (var i = 0; i < tds.length; i++) {
        tds[i].style.color = "white";
    }


}

// 单元格还原
function clearByColor() {
    var td = event.srcElement;

    // td.parentElement 表示获取td的父元素 -》 TR
    var tr = td.parentElement;

    // 如果想要通过js代码设置某节点的样式，则需要加上 .style
    tr.style.backgroundColor = "transparent";

    // tr.cells表示获取这个tr中的所以单元格
    var tds = tr.cells;
    for (var i = 0; i < tds.length; i++) {
        tds[i].style.color = "black";
    }
}

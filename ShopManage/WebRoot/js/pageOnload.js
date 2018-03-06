var _PageHeight = document.documentElement.clientHeight,
 _PageWidth = document.documentElement.clientWidth;
var _LoadingTop = _PageHeight > 61 ? (_PageHeight - 61) / 2 : 0,
 _LoadingLeft = _PageWidth > 215 ? (_PageWidth - 215) / 2 : 0;
var _LoadingHtml = '<div id="loadingDiv"'+ 
		'style="position:absolute;left:0;width:100%;height:' + _PageHeight + 'px;top:0;background:white;opacity:0.8;filter:alpha(opacity=80);z-index:10000;">'
			+'<div style="position: absolute; cursor: wait; left: ' + _LoadingLeft + 'px; top:' + _LoadingTop + 'px; width:200px; height: 57px; line-height: 57px; background: #fff url(image/preloader.gif) no-repeat;background-size:100%,100%;border-radius:15px; background-position: 10px -71px;color: #696969; font-family:\'Microsoft YaHei\';"></div></div>';
document.write(_LoadingHtml);
document.onreadystatechange = completeLoading;
function completeLoading() {

    if (document.readyState == "complete") {

        var loadingMask = document.getElementById('loadingDiv');

        loadingMask.parentNode.removeChild(loadingMask);

    }

}

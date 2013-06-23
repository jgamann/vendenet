/* ================================================================ 
This copyright notice must be untouched at all times.
Copyright (c) 2008 Stu Nicholls - stunicholls.com - all rights reserved.
=================================================================== */

function slideshow() {

/* set up defaults */
minPic = 0;
maxPic = jQuery("#images img").length;
minRow = 0;
maxRow = (maxPic/7);
if (maxRow > parseInt(maxRow)) {maxRow = parseInt(maxRow)+1;}


jQuery("#images img").each(function(){
	jQuery(this).css("margin-left", (((480-(jQuery(this).width()))/2))+"px");
	jQuery(this).css("margin-right", (((480-(jQuery(this).width()))/2)+15)+"px");
	jQuery(this).css("margin-top", (((320-(jQuery(this).height()))/2))+"px");
});


var thumbs = jQuery("#thumbHolder img");
var img = jQuery("#images img");
var txt = jQuery("#txt p");
state = "hide";

//picWidth = img.eq(0).width();
picWidth = 500;
picHeight = 322;
img.eq(minPic).animate({"left": "0px"}, 400, "swing");

thumbs.animate({opacity: 0.6});
thumbs.eq(0).animate({opacity: 1});
thumbs.eq(0).css("background","#fff");

jQuery("#opacity").css("opacity", "0.6");

/* set auto run delay time in milliseconds */
timeOut = 5000000;
t=setTimeout("timedCount()",timeOut);

/* control pad functions */
jQuery("#scrollDown").click(function(){
clearTimeout(t);
minRow++
if (minRow == maxRow) {minRow = 0;}
jQuery("#thumbHolder").animate({"top": -((minRow)*48)+"px"}, 400, "swing");
});

jQuery("#scrollUp").click(function(){
clearTimeout(t);
minRow--
if (minRow == -1) {minRow = maxRow-1;}
jQuery("#thumbHolder").animate({"top": -((minRow)*48)+"px"}, 400, "swing");
});

jQuery("#next").click(function(){
clearTimeout(t);
txtHide(txt)
test = parseInt(minPic/7);
if (test != minRow) {
minRow = test;
jQuery("#thumbHolder").animate({"top": -((minRow)*48)+"px"}, 400, "swing");
}
img.eq(minPic).animate({"left": "-"+picWidth+"px"}, 400, "swing");
thumbs.eq(minPic).animate({opacity: 0.6});
thumbs.eq(minPic).css("background","#aaa");
minPic++
if (minPic == maxPic) {minPic = 0;}
if ((minPic/7) == parseInt(minPic/7)) {
minRow = minPic/7;
jQuery("#thumbHolder").animate({"top": -((minRow)*48)+"px"}, 400, "swing");
}
img.eq(minPic).css("left", picWidth+"px");
img.eq(minPic).animate({"left": "0px"}, 400, "swing");
thumbs.eq(minPic).animate({opacity: 1});
thumbs.eq(minPic).css("background","#fff");
});

jQuery("#previous").click(function(){
clearTimeout(t);
txtHide(txt);
test = parseInt(minPic/7);
if (test != minRow) {
minRow = test;
jQuery("#thumbHolder").animate({"top": -((minRow)*48)+"px"}, 400, "swing");
}
img.eq(minPic).animate({"left": picWidth+"px"}, 400, "swing");
thumbs.eq(minPic).animate({opacity: 0.6});
thumbs.eq(minPic).css("background","#aaa");
minPic--
if (minPic == -1) {minPic = maxPic-1;}
if ((minPic+1)/7 == parseInt((minPic+1)/7) || minPic == maxPic-1) {
if (minPic == maxPic-1) {
minRow = maxRow-1;
}
else {
minRow = (minPic+1)/7-1;
}

if (minRow == -1) {minRow = maxRow-1;}
jQuery("#thumbHolder").animate({"top": -((minRow)*48)+"px"}, 400, "swing");
}
img.eq(minPic).css("left","-"+picWidth+"px");
img.eq(minPic).animate({"left": "0px"}, 400, "swing");
thumbs.eq(minPic).animate({opacity: 1});
thumbs.eq(minPic).css("background","#fff");
});

/* click a thumbnail image function */
thumbs.click(function() {
//clearTimeout(t);
//txtHide(txt);
current = (jQuery(this).index());
//picWidth=img.eq(current).width();
if (current > minPic) {
img.eq(minPic).animate({"left": "-"+picWidth+"px"}, 400, "swing");
thumbs.eq(minPic).animate({opacity: 0.6});
thumbs.eq(minPic).css("background","#aaa");
minPic = current;
img.eq(minPic).css("left", picWidth+"px");

img.eq(minPic).css("margin-left", (480-(img.eq(current).width()))/2+"px");
img.eq(minPic).css("margin-top", (320-(img.eq(current).height()))/2+"px");
img.eq(minPic).animate({"left": "0px"}, 400, "swing");
thumbs.eq(minPic).animate({opacity: 1});
thumbs.eq(minPic).css("background","#fff");
}
if (current < minPic) {
img.eq(minPic).animate({"left": picWidth+"px"}, 400, "swing");
thumbs.eq(minPic).animate({opacity: 0.6});
thumbs.eq(minPic).css("background","#aaa");
minPic = current;
img.eq(minPic).css("left","-"+picWidth+"px");
img.eq(minPic).css("margin-top", (320-(img.eq(current).height()))/2+"px");
img.eq(minPic).css("margin-left", (480-(img.eq(current).width()))/2+"px");
img.eq(minPic).animate({"left": "0px"}, 400, "swing");
thumbs.eq(minPic).animate({opacity: 1});
thumbs.eq(minPic).css("background","#fff");
}

});

/* show/hide information panel */ 
jQuery("#info").click(function(){
clearTimeout(t);
txtToggle(txt);
});

/* stop/strat timer */
jQuery("#slideshow").mouseover(function() {
	clearTimeout(t);
});
jQuery("#slideshow").mouseout(function() {
t=setTimeout("timedCount()",timeOut);
});


}

function txtToggle(txt) {
if (state == "hide") {
state= "show";
jQuery("#opacity").animate({"top": "0px"}, 400, "swing");
txt.eq(minPic).animate({"top": "0px"}, 400, "swing");
}
else {
state="hide";
jQuery("#opacity").animate({"top": "-360px"}, 400, "swing");
txt.eq(minPic).animate({"top": "-360px"}, 400, "swing");
}
}

function txtHide(txt) {
if (state == "show") {
state="hide";
jQuery("#opacity").animate({"top": "-360px"}, 600, "swing");
txt.eq(minPic).animate({"top": "-360px"}, 600, "swing");
}
}

/* auto run image control */ 
function timedCount() {
	var thumbs = jQuery("#thumbHolder img");
	var img = jQuery("#images img");
	var txt = jQuery("#txt p");
	txtHide(txt);
	test = parseInt(minPic/7);
	if (test != minRow) {
	minRow = test;
	jQuery("#thumbHolder").animate({"top": -((minRow)*48)+"px"}, 400, "swing");
	}
	img.eq(minPic).animate({"left": "-"+picWidth+"px"}, 400, "swing");
	thumbs.eq(minPic).animate({opacity: 0.6});
	thumbs.eq(minPic).css("background","#aaa");
	minPic++
	if (minPic == maxPic) {minPic = 0;}
	if ((minPic/7) == parseInt(minPic/7)) {
	minRow = minPic/7;
	jQuery("#thumbHolder").animate({"top": -((minRow)*48)+"px"}, 400, "swing");
	}
	img.eq(minPic).css("left", picWidth+"px");
	img.eq(minPic).animate({"left": "0px"}, 400, "swing");
	thumbs.eq(minPic).animate({opacity: 1});
	thumbs.eq(minPic).css("background","#fff");
	t=setTimeout("timedCount()",timeOut);
}

window.onload=slideshow; 

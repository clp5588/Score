$(function () {
    $(".tablesorter").tablesorter();
    $(".tab_content").hide();
    $("ul.tabs li:first").addClass("active").show();
    $(".tab_content:first").show();

    $("ul.tabs li").click(function () {
        $("ul.tabs li").removeClass("active");
        $(this).addClass("active");
        $(".tab_content").hide();
        var activeTab = $(this).find("a").attr("href");
        $(activeTab).fadeIn();
        return false;
    });
    var clientHeight = $(window).height();
    var headerHeight = $("#header").height();
    var sidebarHeight= $("#secondary_bar").height();
    $("#sidebar").height(clientHeight-headerHeight-sidebarHeight);
    $("#main").height(clientHeight-headerHeight-sidebarHeight);
    $("#sidebar").css("overflow","auto");
    $("#main").css("overflow","auto");
});
function logout() {
    $("#logout").find("form").submit();
};
function viewPage(url, name) {
    $(".section_title").text(name);
    $("a.current").text(name);
    $("#main").load(url);
}
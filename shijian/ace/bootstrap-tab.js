var addTabs = function (options) {
 //var rand = Math.random().toString();
 //var id = rand.substring(rand.indexOf('.') + 1);
 var url = window.location.protocol + '//' + window.location.host;
 options.url = url + options.url;
 id = "tab_" + options.id;
 $(".active").removeClass("active");
 //���TAB�����ڣ�����һ���µ�TAB
 if (!$("#" + id)[0]) {
  //�̶�TAB��IFRAME�߶�
  mainHeight = $(document.body).height() - 90;
  //������TAB��title
  title = '<li role="presentation" id="tab_' + id + '"><a href="#' + id + '" aria-controls="' + id + '" role="tab" data-toggle="tab">' + options.title;
  //�Ƿ������ر�
  if (options.close) {
   title += ' <i class="glyphicon glyphicon-remove" tabclose="' + id + '"></i>';
  }
  title += '</a></li>';
  //�Ƿ�ָ��TAB����
  if (options.content) {
   content = '<div role="tabpanel" class="tab-pane" id="' + id + '">' + options.content + '</div>';
  } else {//û�����ݣ�ʹ��IFRAME������
   content = '<div role="tabpanel" class="tab-pane" id="' + id + '"><iframe src="' + options.url + '" width="100%" height="' + mainHeight +
     '" frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="yes" allowtransparency="yes"></iframe></div>';
  }
  //����TABS
  $(".nav-tabs").append(title);
  $(".tab-content").append(content);
 }
 //����TAB
 $("#tab_" + id).addClass('active');
 $("#" + id).addClass("active");
};
var closeTab = function (id) {
 //����رյ��ǵ�ǰ�����TAB����������ǰһ��TAB
 if ($("li.active").attr('id') == "tab_" + id) {
  $("#tab_" + id).prev().addClass('active');
  $("#" + id).prev().addClass('active');
 }
 //�ر�TAB
 $("#tab_" + id).remove();
 $("#" + id).remove();
};
$(function () {
 mainHeight = $(document.body).height() - 45;
 $('.main-left,.main-right').height(mainHeight);
 $("[addtabs]").click(function () {
  addTabs({ id: $(this).attr("id"), title: $(this).attr('title'), close: true });
 });
 
 $(".nav-tabs").on("click", "[tabclose]", function (e) {
  id = $(this).attr("tabclose");
  closeTab(id);
 });
});
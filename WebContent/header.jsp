<%@ page import="com.cqut.recruitPortal.dto.NewsTypeNews" %>
<%@ page import="java.util.List" %>
<%@ page import="com.cqut.recruitPortal.entity.NewsType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<nav class="navbar navbar-default">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="">品牌网</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <%
                    List<NewsTypeNews> list1 = (List<NewsTypeNews>)request.getSession().getAttribute("category_list");
                    int size1 = list1.size();
                    for(int i=0;i<size1;i++){
                        NewsType newsType = list1.get(i).getNewsType();
                %>
                <li><a href="Index?actionType=newsTypeQuery&newsType=<%=newsType.getNewsTypeID()%>"><%=newsType.getName()%></a></li>
                <%}%>
            </ul>
            <form class="navbar-form navbar-left" role="search" method="post" action="Index">
                <div class="form-group">
                    品牌关键字：<input type="text" class="form-control" name="title">
                    <input type="hidden" name="actionType" value="indexQuery"/>
                </div>
                <button type="submit" class="btn btn-default">查询</button>
            </form>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="Index?actionType=recent">最近浏览</a></li>
                <%--<li class="dropdown">--%>
                    <%--<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>--%>
                    <%--<ul class="dropdown-menu">--%>
                        <%--<li><a href="#">Action</a></li>--%>
                        <%--<li><a href="#">Another action</a></li>--%>
                        <%--<li><a href="#">Something else here</a></li>--%>
                        <%--<li role="separator" class="divider"></li>--%>
                        <%--<li><a href="#">Separated link</a></li>--%>
                    <%--</ul>--%>
                <%--</li>--%>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js"
            integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"></script>
</head>
<body>
<div class="container-fluid">
    <div class="row flex-nowrap">
        <div class="col-lg-2 col-md-2 col-xl-2 px-sm-2 px-0 bg-secondary bg-gradient">
            <div class="d-flex flex-column align-items-center align-items-sm-start px-3 pt-2 text-white min-vh-100">
                <a href="/" class="d-flex align-items-center pb-3 mb-md-0 me-md-auto text-white text-decoration-none">
                </a>


                <div class="dropdown">
                    <a class="btn btn-secondary dropdown-toggle" href="/sell/home" role="button" id="dropdownMenuLink2"
                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Tài khoản
                    </a>
                    <div class="dropdown-menu" aria-labelledby="dropdownMenuLink2">
                        <a class="dropdown-item" href="/sell/login">Đăng nhập</a>
                        <a class="dropdown-item" href="/sell/logout" onclick="return confirm('xác nhận đăng xuất')">Đăng
                            xuất</a>
                    </div>
                </div>

                <div class="dropdown">
                    <a class="btn btn-secondary " href="/sell/home" role="">
                        Bán hàng
                    </a>
                </div>


                <div class="dropdown">
                    <a class="btn btn-secondary dropdown-toggle" href="/sell/home" role="button" id="dropdownMenuLink"
                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Quản lý
                    </a>

                    <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                        <a class="dropdown-item" href="/mau-sac/index">Quản lý màu sắc</a>
                        <a class="dropdown-item" href="/nhan-vien/index">Quản lý nhân viên</a>
                        <a class="dropdown-item" href="/san-pham/index">Quản lý sản phẩm</a>
                        <a class="dropdown-item" href="/kich-thuoc/index">Quản lý kích thước</a>
                        <a class="dropdown-item" href="/sp-chi-tiet/index">Quản lý sản phẩm chi tiết</a>
                        <a class="dropdown-item" href="/khach-hang/index">Quản lý khách hàng</a>
                        <a class="dropdown-item" href="/hoa-don/index">Quản lý hoá đơn</a>
                        <a class="dropdown-item" href="/hoa-don-chi-tiet/index">Quản lý hoá đơn chi tiết</a>
                    </div>
                </div>

                <hr>
            </div>
        </div>
        <div class="col py-3">
            <h2 style="text-align: center"> QUẢN LÝ HOÁ ĐƠN</h2>

<%--            <a href="create">--%>
<%--                <button class="btn btn-success">Thêm mới</button>--%>
<%--            </a>--%>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Nhân Viên</th>
                    <th>Khách Hàng</th>
                    <th>Ngày tao</th>
                    <th>Trạng thái</th>
<%--                    <th colspan="2">Thao Tác</th>--%>
                </tr>
                </thead>
                <tbody>

                <c:forEach items="${pageData.content}" var="st">
                    <tr>
                        <td>${st.ID}</td>
                        <td>
                            <c:forEach items="${listNV}" var="it">
                                <c:if test="${it.ID eq st.idNV}">
                                    <p>${it.ten}</p>
                                </c:if>
                            </c:forEach>
                        </td>
                        <td>
                            <c:forEach items="${listKH}" var="it">
                                <c:if test="${it.ID eq st.idKH}">
                                    <p>${it.ten}</p>
                                </c:if>
                            </c:forEach>
                        </td>
                        <td><fmt:formatDate value="${st.ngayMuaHang}" pattern="dd/MM/yyyy"/></td>
                        <td>${st.trangThai==1?"Đã thanh toán":""}
                                ${st.trangThai==0?"Chưa thanh toán":""}
                                ${st.trangThai==2?"Đã Huỷ":""}</td>
<%--                        <td><a href="/hoa-don/edit/${st.ID}" class="btn btn-warning">Update</a></td>--%>
<%--                        <td><a href="/hoa-don/delete/${st.ID}" return onclick="confirm('xác nhận') "--%>
<%--                               class="btn btn-danger">Delete</a></td>--%>
                    </tr>
                </c:forEach>


                </tbody>
            </table>

            <nav aria-label="Page navigation example">
                <ul class="pagination">
                    <c:if test="${pageData.number > 0}">
                        <li class="page-item">
                            <a class="page-link" href="/hoa-don/index?page=${pageData.number - 1}">Previous</a>
                        </li>
                    </c:if>

                    <li class="page-item"><a class="page-link disabled" href="#">${pageData.number + 1}</a></li>


                    <c:if test="${pageData.number +1 < pageData.getTotalPages()}">
                        <li class="page-item">
                            <a class="page-link" href="/hoa-don/index?page=${pageData.number + 1}">Next</a>
                        </li>
                    </c:if>


                </ul>
            </nav>


        </div>
    </div>
</div>

</body>
</html>


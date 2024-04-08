<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Quản lý Đơn Hàng</title>
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

<div>
    <div class="container-fluid">
        <h3 class="text-center">SELLER CENTER</h3>
        <div class="mb-0 me-5 d-flex justify-content-between">
            <div class="a">
                <a href="/mau-sac/index" class="btn btn-danger"> < ADMIN</a>
            </div>
            <div class="b">
                <a href="/sell/lich-su-thanh-toan" class="btn btn-success">LỊCH SỬ THANH TOÁN ></a>
            </div>
        </div>

        <div class="row">

            <div class="col-md-8">
                <div class="card mt-4">
                    <div class="card-header bg-primary text-white">
                        <h5>Sản phẩm chi tiết</h5>
                    </div>
                    <div class="card-body">
                        <table class="table table-striped">
                            <thead>
                            <tr>
                                <th>ID</th>
                                <th>mã SPCT</th>
                                <th>Kích thước</th>
                                <th>Màu sắc</th>
                                <th>Sản phẩm</th>
                                <th>Số lượng</th>
                                <th>Đơn giá</th>
                                <th>Thao Tác</th>
                            </tr>
                            </thead>
                            <tbody>

                            <c:forEach items="${listSPCT.content}" var="st">
                                <c:if test="${st.soLuong>0}">
                                    <tr>
                                        <td>${st.ID}</td>
                                        <td>${st.maSPCT}</td>
                                        <td>
                                            <c:forEach items="${listKT}" var="it">
                                                <c:if test="${it.ID eq st.idKichThuoc}">
                                                    <p>${it.ten}</p></c:if>
                                            </c:forEach>
                                        </td>

                                        <td>
                                            <c:forEach items="${listMS}" var="it">
                                                <c:if test="${it.ID eq st.idMauSac}">
                                                    <p>${it.ten}</p></c:if>
                                            </c:forEach>
                                        </td>

                                        <td>
                                            <c:forEach items="${listSP}" var="it">
                                                <c:if test="${it.ID eq st.idSanPham}">
                                                    <p>${it.ten}</p></c:if>
                                            </c:forEach>
                                        </td>

                                        <td>${st.soLuong}</td>
                                        <td>${st.donGia}</td>


                                        <td>
                                            <a href="/sell/add-to-cart/${st.ID}?pageSPCT=${param.pageSPCT}"
                                               class="btn btn-primary">Thêm </a></td>
                                    </tr>
                                </c:if>
                            </c:forEach>


                            </tbody>
                        </table>
                        <nav aria-label="Page navigation example">
                            <ul class="pagination">
                                <c:if test="${listSPCT.number > 0}">
                                    <li class="page-item">
                                        <a class="page-link"
                                           href="?pageHDCT=${listHDCT.number }&pageSPCT=${param.pageSPCT -1}">Previous</a>

                                    </li>
                                </c:if>

                                <li class="page-item"><a class="page-link disabled" href="#">${listSPCT.number + 1}</a>
                                </li>


                                <c:if test="${listSPCT.number +1 < listSPCT.getTotalPages()}">
                                    <li class="page-item">
                                        <a class="page-link"
                                           href="?pageHDCT=${listHDCT.number }&pageSPCT=${param.pageSPCT +1}">Next</a>
                                    </li>
                                </c:if>


                            </ul>
                        </nav>
                    </div>
                </div>

                <div class="card mt-4">
                    <div class="card-header bg-secondary text-white">
                        <h5>Giỏ hàng</h5>
                    </div>
                    <div class="card-body">
                        <table class="table table-striped">
                            <thead>
                            <tr>
                                <th>ID</th>
                                <th>IDSPCT</th>
                                <th>sản phẩm</th>
                                <th>màu sắc</th>
                                <th>kích thước</th>
                                <th>Số lượng</th>
                                <th>Đơn giá</th>
                                <th>Thao Tác</th>
                            </tr>
                            </thead>
                            <tbody>

                            <c:forEach items="${listHDCT.content}" var="st">
                                <tr>
                                    <td>${st.ID}</td>
                                    <td>${st.idSPCT}</td>

                                    <td>
                                        <c:set var="filteredSPCT" value=""/>
                                        <c:forEach items="${listSPCTnoPT}" var="spct">
                                            <c:if test="${spct.ID eq st.idSPCT}">
                                                <c:set var="filteredSPCT" value="${spct}"/>
                                            </c:if>
                                        </c:forEach>
                                        <c:if test="${not empty filteredSPCT}">
                                            <c:forEach items="${listSP}" var="st3">
                                                <c:if test="${st3.ID eq filteredSPCT.idSanPham}">
                                                    <p>${st3.ten}</p>
                                                </c:if>
                                            </c:forEach>
                                        </c:if>
                                    </td>


                                    <td>
                                        <c:set var="filteredSPCT" value=""/>
                                        <c:forEach items="${listSPCTnoPT}" var="spct">
                                            <c:if test="${spct.ID eq st.idSPCT}">
                                                <c:set var="filteredSPCT" value="${spct}"/>
                                            </c:if>
                                        </c:forEach>
                                        <c:if test="${not empty filteredSPCT}">
                                            <c:forEach items="${listMS}" var="st3">
                                                <c:if test="${st3.ID eq filteredSPCT.idMauSac}">
                                                    <p>${st3.ten}</p>
                                                </c:if>
                                            </c:forEach>
                                        </c:if>
                                    </td>

                                    <td>
                                        <c:set var="filteredSPCT" value=""/>
                                        <c:forEach items="${listSPCTnoPT}" var="spct">
                                            <c:if test="${spct.ID eq st.idSPCT}">
                                                <c:set var="filteredSPCT" value="${spct}"/>
                                            </c:if>
                                        </c:forEach>
                                        <c:if test="${not empty filteredSPCT}">
                                            <c:forEach items="${listKT}" var="st3">
                                                <c:if test="${st3.ID eq filteredSPCT.idKichThuoc}">
                                                    <p>${st3.ten}</p>
                                                </c:if>
                                            </c:forEach>
                                        </c:if>
                                    </td>


                                    <td>
                                        <form action="/sell/edit-so-luong" method="post">
                                            <input type="text" name="soLuong"  value="${st.soLuong}">
                                            <input type="hidden" name="ID" value="${st.ID}">
                                            <button type="submit">Cập nhật</button>
                                        </form>
                                    </td>

                                    <td>${st.donGia}</td>
                                    <td><a href="/sell/delete-gio-hang/${st.ID}" return onclick="confirm('xác nhận') "
                                           class="btn btn-danger">Xoá</a></td>


                                </tr>
                            </c:forEach>


                            </tbody>
                        </table>
                        <nav aria-label="Page navigation example">
                            <ul class="pagination">
                                <c:if test="${listHDCT.number > 0}">
                                    <li class="page-item">
                                        <a class="page-link"
                                           href="?pageHDCT=${listHDCT.number -1}&pageSPCT=${param.pageSPCT}">Previous</a>
                                    </li>
                                </c:if>

                                <li class="page-item"><a class="page-link disabled" href="#">${listHDCT.number + 1}</a>
                                </li>


                                <c:if test="${listHDCT.number +1 < listHDCT.getTotalPages()}">
                                    <li class="page-item">
                                        <a class="page-link"
                                           href="?pageHDCT=${listHDCT.number + 1}&pageSPCT=${param.pageSPCT}">Next</a>
                                    </li>
                                </c:if>


                            </ul>
                        </nav>
                    </div>
                </div>
            </div>

            <div class="col-md-4">
                <div class="card mt-4">
                    <div class="card-header bg-success text-white">
                        <h5>Hoá đơn</h5>
                    </div>
                    <div class="card-body" id="test">
                        <h3 class="myclass" STYLE="text-align: center">Danh sách hoá đơn</h3>
                        <a href="/sell/create-hoa-don-cho" class="btn btn-success mt-3 mb-2">Thêm mới </a>

                        <br>

                        <nav>
                            <div class="nav nav-tabs" id="nav-tab" role="tablist">
                                <c:forEach items="${listHDC}" var="st">
                                    <a class=" ml-3  nav-link ${data.ID eq st.ID?"active bg-secondary text-white":"" }"
                                       href="/sell/edit-hoa-don/${st.ID}?pageSPCT=${param.pageSPCT}"> HD${st.ID}</a>
                                </c:forEach>
                            </div>
                        </nav>





                        <form method="post" action="/sell/thanh-toan">
                            <div class="tab-content" id="nav-tabContent">
                                <div class="form-group mt-5">
                                    <label>ID hoá đơn</label>
                                    <input class="form-control" name="ID" value="${data.ID}" readonly="true">
                                </div>



                                <div class="row d-flex justify-content-between" >
                                <div class="col-md-6">
                                <div id="searchContainer" class="form-group ">
                                    <label>Tìm kiếm</label>
                                    <div class="input-group">
                                        <input type="search" id="searchInput" class="form-control rounded"
                                               placeholder="Nhập SDT cần tìm" aria-label="Search"
                                               aria-describedby="search-addon"/>
                                        <button class="btn btn-outline-primary" onclick="performSearch(event)">
                                            Tìm kiếm
                                        </button>
                                    </div>
                                </div>
                                </div>

                                <div class="col-md-6">
                                <div class="form-group ">
                                    <label>SDT khách hàng</label>
                                    <select name="idKH" class="form-control">
                                        <option value=""></option>
                                        <c:forEach items="${listKH}" var="it">
                                            <option value="${it.ID}" ${it.ID eq data.idKH ? 'selected' : ''}>
                                                    ${it.SDT}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>
                                </div>
                                </div>

                                <script>
                                    function performSearch(event) {
                                        // Ngăn chặn hành vi gửi form mặc định
                                        event.preventDefault();

                                        var sdtValue = document.getElementById('searchInput').value.trim();
                                        if (sdtValue !== '') {
                                            window.location.href = "http://localhost:8080/sell/tim-kiem-khach-hang?SDT=" + encodeURIComponent(sdtValue);
                                        }
                                    }
                                </script>

                                <div class="form-group">
                                    <p class="form-control">
                                        <fmt:formatDate value="${data.ngayMuaHang}" pattern="dd/MM/yyyy"/>
                                    </p>
                                </div>
                            </div>

                            <div class="col-sm-12">
                                <div class="card mb-2 mt-4">
                                    <div class="mb-0 me-5 d-flex justify-content-around">
                                        <div class="a">
                                            <h3 class="lead text-danger">Hoá đơn chọn: ID:${IDHoaDon} </h3>
                                        </div>
                                        <div class="b">
                                            <h3 class="lead text-danger">Tổng tiền: ${tongTien} VND </h3>
                                        </div>
                                    </div>

                                </div>
                                <p style="color: red; font-size: 25px"> ${message}</p>
                                <div class="d-flex justify-content-end " style="margin-top: 50px">


                                    <button type="submit" class="btn btn-warning btn-lg"
                                            onclick="return confirm('xác nhận thanh toán ?')">Thanh toán
                                    </button>
<%--                                    <c:if test="${not empty data.ID}">--%>
<%--                                        <a class="btn btn-danger" href="/sell/huy-hoa-don/${data.ID}"--%>
<%--                                           onclick="return confirm('xác nhận xoá ?')"> Huỷ </a>--%>

<%--                                    </c:if>--%>

                                </div>
                            </div>

                        </form>

                    </div>
                </div>


            </div>
        </div>
    </div>
</div>


<style>
    .card-body {
        max-height: 800px; /* Đặt chiều cao tối đa của phần tử cuộn */
        overflow: auto; /* Cho phép cuộn khi nội dung vượt quá chiều cao tối đa */
    }
</style>
</body>
</html>



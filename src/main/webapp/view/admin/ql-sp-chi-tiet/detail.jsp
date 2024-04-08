<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ taglib prefix="c" uri="jakarta.tags.core" %>--%>
<%@ taglib prefix="f" uri="jakarta.tags.functions" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
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
<div class="container" style="margin-top: 10px;">
    <div class="row"
         style="border: 1px darkgrey solid; border-radius: 10px; width: 50%; margin: 0 auto; padding: 20px;">
        <div class="col-sm-12">
            <h2 class="myclass" STYLE="text-align: center">CHI TIẾT</h2>
            <div class="form-group">
                <label>Sản phẩm</label>
                <c:forEach items="${listSP}" var="it">
                    <c:if test="${it.ID eq data.idSanPham}">
                        <p class="form-control">
                                ${it.ten}
                        </p>
                    </c:if>
                </c:forEach>
            </div>
            <div class="form-group">
                <label>Màu sắc</label>
                <c:forEach items="${listMS}" var="it">
                    <c:if test="${it.ID eq data.idMauSac}">
                        <p class="form-control">
                                ${it.ten}
                        </p>
                    </c:if>
                </c:forEach>
            </div>

            <div class="form-group">
                <label>Kích thước</label>
                <c:forEach items="${listKT}" var="it">
                    <c:if test="${it.ID eq data.idKichThuoc}">
                        <p class="form-control">
                                ${it.ten}
                        </p>
                    </c:if>
                </c:forEach>
            </div>

            <div class="form-group">
                <label>Mã SPCT</label>
                <p class="form-control"> ${data.maSPCT}</p>
            </div>

            <div class="form-group">
                <label>Số lượng</label>
                <p class="form-control"> ${data.soLuong}</p>

            </div>
            <div class="form-group">
                <label>Đơn giá</label>
                <p class="form-control"> ${data.donGia}</p>
            </div>


            <div class="form-group">
                <label>Trạng thái</label>
                <br>
                <p class="form-control">${data.trangThai==1?"Kích hoạt":"Không kích hoạt"}</p>
            </div>


            <div class="d-flex justify-content-between">
                <a href="/sp-chi-tiet/index" class="btn btn-primary ">Quay lại</a>
            </div>


        </div>
    </div>
</div>
</body>
</html>



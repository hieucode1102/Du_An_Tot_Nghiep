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
            <h2 class="myclass" STYLE="text-align: center">CẬP NHẬT</h2>
            <sf:form method="post" action="/sp-chi-tiet/update/${data.ID}" modelAttribute="data">
                <div class="form-group">
                    <label>Mã Màu sắc</label>
                    <select name="idMauSac" class="form-control"  >
                        <option></option>
                        <c:forEach items="${listMS}" var="it">
                            <option value="${it.ID}" ${it.ID eq data.idMauSac ? 'selected' : ''} >
                                    ${it.ma} - ${it.ten}
                            </option>
                        </c:forEach>
                    </select>
                    <sf:errors path="idMauSac" cssStyle="color: red"></sf:errors>
                </div>

                <div class="form-group">
                    <label>Mã kích thước</label>
                    <select name="idKichThuoc" class="form-control"  >
                        <option></option>
                        <c:forEach items="${listKT}" var="it">
                            <c:if test="${it.trangThai eq 1}">
                                <option value="${it.ID}" ${it.ID == data.idKichThuoc ? 'selected' : ''} >
                                        ${it.ma} - ${it.ten}
                                </option>
                            </c:if>
                        </c:forEach>
                    </select>
                    <sf:errors path="idKichThuoc" cssStyle="color: red"></sf:errors>
                </div>

                <div class="form-group">
                    <label>Mã Sản phẩm</label>
                    <select name="idSanPham" class="form-control"   >
                        <option></option>
                        <c:forEach items="${listSP}" var="it">
                            <c:if test="${it.trangThai eq 1}">
                                <option value="${it.ID}" ${it.ID eq data.idSanPham ? 'selected' : ''} >
                                        ${it.ma} - ${it.ten}
                                </option>
                            </c:if>
                        </c:forEach>
                    </select>
                    <sf:errors path="idSanPham" cssStyle="color: red"></sf:errors>
                </div>

                <div class="form-group">
                    <label>Mã SPCT</label>
                    <sf:input class="form-control" type="text" name="maSPCT" path="maSPCT"/>
                    <sf:errors path="maSPCT" cssStyle="color: red"></sf:errors>
                </div>

                <div class="form-group">
                    <label>Số lượng</label>
                    <sf:input path="soLuong" class="form-control" type="number" max="2147483647" title="Số lượng quá lớn"/>
                    <sf:errors path="soLuong" cssStyle="color: red"></sf:errors>
                </div>
                <div class="form-group">
                    <label>Đơn giá</label>
                    <sf:input class="form-control" type="number" name="donGia" path="donGia"/>
                    <sf:errors path="donGia" cssStyle="color: red"></sf:errors>
                </div>


                <div class="d-flex justify-content-between">
                    <button class="btn btn-success" type="submit" onclick="return confirm('Xác nhận ?')">Cập nhật
                    </button>
                    <a href="/sp-chi-tiet/index" class="btn btn-primary ">Quay lại</a>
                </div>
                <h4 style="color: red">${message}</h4>


            </sf:form>
        </div>
    </div>
</div>
</body>
</html>



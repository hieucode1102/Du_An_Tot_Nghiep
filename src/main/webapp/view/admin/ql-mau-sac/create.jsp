<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="f" uri="jakarta.tags.functions" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
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
            <h2 class="myclass" STYLE="text-align: center">THÊM MỚI</h2>
            <sf:form method="post" action="/mau-sac/store" modelAttribute="data">
                <div class="form-group">
                    <label>Mã</label>
                    <sf:input class="form-control" type="text" name="ma"  path="ma"/>
                    <sf:errors path="ma" cssStyle="color: red"></sf:errors>
                </div>
                <div class="form-group">
                    <label>Tên</label>
                    <sf:input class="form-control" type="text" name="ten" path="ten"/>
                    <sf:errors path="ten" cssStyle="color: red"></sf:errors>
                </div>
                <div class="form-group">
                    <label>Trạng thái</label>
                    <br>
                    <sf:radiobutton path="trangThai" value="1" label="Kích hoạt"/>
                    <sf:radiobutton path="trangThai" value="0" label="Không kích hoạt"/>
                </div>

                <div class="d-flex justify-content-between">
                    <button class="btn btn-success" type="submit" onclick="return confirm('Xác nhận ?')">Thêm mới</button>
                    <a href="/mau-sac/index" class="btn btn-primary ">Quay lại</a>
                </div>
                <h4 style="color: red">${message}</h4>

            </sf:form>
        </div>
    </div>
</div>
</body>
</html>

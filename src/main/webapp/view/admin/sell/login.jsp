<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="f" uri="jakarta.tags.functions" %>
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
<div class="container" style="margin-top: 50px;">
    <div class="row"
         style="border: 1px darkgrey solid; border-radius: 10px;width: 50%; margin: 0 auto; padding: 20px;">
        <div class="col-sm-12">
            <h3>Đăng nhập</h3>

            <form action="/sell/checklogin" method="post">

                <div class="form-group">
                    <label>UserName</label>
                    <input type="text" class="form-control" name="tenDangNhap" placeholder="Enter tên đăng nhập" required>
                </div>
                <div class="form-group">
                    <label>Mật khẩu:</label>
                    <input type="password" class="form-control" name="matKhau" placeholder="Enter mật khẩu"
                           required>
                </div>

                <p style="color: red" >${messageLG}</p>
                <button type="submit" class="btn btn-primary">Đăng nhập</button>
                <button type="reset" class="btn btn-primary">Cancel</button>


            </form>
        </div>
    </div>
</div>
</body>
</html>

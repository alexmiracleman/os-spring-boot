<!doctype html>
<html lang="en" xmlns="http://www.w3.org/1999/html" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1" name="viewport">

    <link crossorigin="anonymous" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" rel="stylesheet">
    <title>Items to add</title>
</head>
<body>
<h3 align="center">ADD NEW ITEMS</h3>
<div class="container">
    <form>
        <table class="table table-striped">
            <thead>
            <tr>

                <th scope="col">Item ID</th>
                <th scope="col">Item Name</th>
                <th scope="col">Price</th>
                <th scope="col">Department</th>
                <th scope="col">Added/Modified</th>

            </tr>
            </thead>
            <tbody>

            <#list items as item>
            <tr>

                <th scope="row">${item.id}</th>
                <td>${item.name}</td>
                <td>$${item.price}</td>
                <td>${item.itemDepartmentType.getName()}</td>
                <td>${item.creationModificationDate}</td>

            </tr>
            </#list>


            </tbody>
        </table>

    </form>

    <div align="center" class="container">
        <p></p>
        <form action="/admin/items/add" method="POST">
            <strong>Select department:
            <select align="center" name="department" style="width:105px;height:35px">
                <#list itemDepartmentTypes as itemDepartmentType>
                <option value="${itemDepartmentType.id}">${itemDepartmentType.getName()}</option>
            </#list>
            </select>
            <p></p>
            <p>Item name: <input type="text" name="name" required="required"/></p>
                <p></p>
            <p>Item price: $ <input type="text" name="price" required="required"/></p>
            </strong>


            <strong>
                <p></p>
                <input class="btn btn-dark" style="width:137px" type="submit" value="SUBMIT">
                <p></p>
                <a href="/admin/items" class="btn btn-dark" style="width:137px" href="/">RETURN</a>

    </div>
    </strong>


    </form>


    <#if errorMessage??>
    <p></p>
    <p align="center"><strong>${errorMessage}</strong></p>
</#if>

<#if successMessage??>
<p></p>
<p align="center"><strong>${successMessage}</strong></p>
</#if>


<script crossorigin="anonymous"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
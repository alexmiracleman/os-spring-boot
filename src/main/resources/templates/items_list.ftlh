<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <title>Items list</title>
</head>
<body>
<h3 align="center">ONLINE STORE MAIN ADMIN</h3>

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

</div>
    <p></p>
        <div class="container" align="center">
            <a href="/admin/items/add" class="btn btn-dark" style="width:137px">ADD ITEMS</a>

    <p></p>

        <a href="/admin/items/update" class="btn btn-dark" style="width:137px">EDIT ITEMS</a>

    <p></p>
        <a href="/admin/items/delete" class="btn btn-dark" style="width:137px">REMOVE ITEMS</a>

</div>
<p></p>
<div class="container" align="center">
    <a href="/logout" class="btn btn-dark" style="width:137px">LOG OUT</a>
</div>

<!-- Optional JavaScript; choose one of the two! -->

<!-- Option 1: Bootstrap Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>

<!-- Option 2: Separate Popper and Bootstrap JS -->
<!--
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
-->
</body>
</html>
<!doctype html>
<html lang="en" xmlns="http://www.w3.org/1999/html" xmlns="http://www.w3.org/1999/html">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
  <title>Items to update</title>
</head>
<body>
<h3 align="center">EDIT ITEM</h3>
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
    <div class="container" align="center">
      <p></p>
      <form action="/admin/items/update" method="POST">

              <strong>
              <div class="form-group">
                  Select item to update:
                  <select name="name" style="width:95px;height:35px" align="center" required>
                  <#list items as item>
                  <option value="${item.name}">${item.name}</option>
              </#list>
                      </select>
              </div>

             <p></p>
              <p></p>
              <p>Edit price: $ <input type="text" name="price" style="width:75px;height:35px" required="required"/></p>

              <p></p>
          </strong>
          <p></p>
        <input type="submit" class="btn btn-dark" style="width:137px" value="SUBMIT">
        <p></p>
        <a href="/admin/items" class="btn btn-dark" style="width:137px">RETURN</a>
      </form>
</div>

  <#if errorMessage??>
  <p></p>
  <p align="center"><strong>${errorMessage}</strong></p>
</#if>

    <#if successMessage??>
    <p></p>
    <p align="center"><strong>${successMessage}</strong></p>
</#if>



<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>

</body>
</html>
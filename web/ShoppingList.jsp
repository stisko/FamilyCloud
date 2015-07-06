

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<div class="col-sm-8 col-xs-12 " id="maincontent">

    <div class="col-sm-12" id="contentheader">
        <div class="media col-sm-4">
            <div class="media-left media-middle">
                <a href="#">
                    <img class="media-object" width="45" height="45" src="img/Shopping.png">
                </a>
            </div>
            <div class="media-body">
                <h4 class="media-heading">Shopping List</h4>
            </div>



        </div>



        <div id="suc_todo_mes" class="${noti_message.classs}">${noti_message.message}</div>

    </div>

    <div class="col-sm-12" id="contentcontent">					

        <div class="col-sm-12">	

            <div class="btn-toolbar" role="toolbar" id="shoppinglisttbuttonbar"> 

                <div class="btn-group" role="group">

                    <a class="btn btn-primary" id="additemshoplist" data-toggle="modal" data-target="#additem">
                        <span class="glyphicon glyphicon-plus-sign" aria-hidden="true">
                        </span>Add Item</a>

                </div>

                <div class="btn-group" role="group">

                    <a class="btn btn-primary" id="custom-prev-shoplist" href="#" role="button"><span class="glyphicon glyphicon-circle-arrow-left" aria-hidden="true"></span> Prev</a>

                    <a class="btn btn-primary" id="custom-next-shoplist" href="#" role="button"><span class="glyphicon glyphicon-circle-arrow-right" aria-hidden="true"></span> Next</a>

                </div>

                <a class="btn btn-primary" id="printitembutton" href="javascript:window.print()" role="button"><span class="glyphicon glyphicon-print" aria-hidden="true"></span>Print Shopping List</a>
            </div>







        </div>
        <div class="row">
            <div class="col-md-12 mavroperivlima">
                <div class="perivlimatable2">
                    <div class="table-responsive">

                        <table class="table" id="shoplisttable">
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th><input class="stat_sort" type="button" value="Title" onclick="sorttableshopSHOP('title')"></th>
                                    <th><input class="stat_sort" type="button" value="Assigned To" onclick="sorttableshopSHOP('assigned_to')"></th>
                                    <th><input class="stat_sort" type="button" value="Created By" onclick="sorttableshopSHOP('created_by')"></th>
                                    <th><input class="stat_sort" type="button" value="Quantity" onclick="sorttableshopSHOP('quantity')"></th>
                                    <th><input class="stat_sort" type="button" value="Price" onclick="sorttableshopSHOP('price')"></th>
                                    <th><input class="stat_sort" type="button" value="Status" onclick="sorttableshopSHOP('status')"></th>
                                    <th></th>
                                    <th></th>							
                                </tr>

                            </thead>
                            <tbody id="changedTableSHOP">
                                <c:forEach items="${familyShopList}" var="familyShopList" varStatus="loopStatus">
                                    <tr>
                                        <td>${loopStatus.index+1}</td>
                                        <td>${familyShopList.shoptitle}</td>
                                        <td>${familyShopList.assigned_to}</td>
                                        <td>${familyShopList.created_by}</td>
                                        <td>${familyShopList.quantity}</td>
                                        <td>${familyShopList.price}</td>                                    
                                        <td>${familyShopList.shopstatus}</td>

                                        <c:choose>
                                            <c:when test="${(familyShopList.created_by eq cuRRENTuserDr.username)||(cuRRENTuserDr.director eq 'Y')}">
                                                <td><button class="btn btn-default"  data-toggle="modal" data-target="#edititem" value="${familyShopList.shopitemID}" onclick="editShopItem(this.value)"><span class="glyphicon glyphicon-pencil">Edit</span></button></td>
                                                <td><button class="btn btn-default"  data-toggle="modal" data-target="#deletemodal" value="${familyShopList.shopitemID}" onclick="deleteShopItem(this.value)"><span class="glyphicon glyphicon-remove">Delete</span></button></td>
                                            </c:when>

                                            <c:otherwise>
                                                <td><button  class="btn btn-default disabled"  data-toggle="modal" data-target="#edititem" value="${familyShopList.shopitemID}" onclick="editShopItem(this.value)"><span class="glyphicon glyphicon-pencil">Edit</span></button></td>
                                                <td><button  class="btn btn-default disabled"  data-toggle="modal" data-target="#deletemodal" value="${familyShopList.shopitemID}" onclick="deleteShopItem(this.value)"><span class="glyphicon glyphicon-remove">Delete</span></button></td>
                                            </c:otherwise>
                                        </c:choose>                                       

                                    </tr>

                                </c:forEach>

                            </tbody>
                        </table>


                    </div>
                </div>
            </div>
        </div>







    </div>


</div>


<div class="modal fade" id="deletemodal" tabindex="-1" role="dialog" aria-labelledby="deletemodalShop" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">Delete</h4>
            </div>
            <div class="modal-body" id="deleteShop_modal_body">


            </div>

        </div>
    </div>
</div>








<div class="modal fade" id="edititem" tabindex="-1" role="dialog" aria-labelledby="edittasklabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">Edit Shop Item</h4>
            </div>
            <div id="editShop_modal_body">
                


            </div>

        </div>
    </div>



</div>


<div class="modal fade" id="additem" tabindex="-1" role="dialog" aria-labelledby="additemlabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">Add Shop Item</h4>
            </div>

            <form id="addshopitemform" name="formaddshop" action="controller_servl" method="post">
                <div class="modal-body">



                    <div class="row">									
                        <div class="col-sm-6">				
                            <label for="titleadditem">Item Title</label>
                            <input type="text" class="form-control" name="titleaddshop" id="titleadditem" oninput="style_inp('titleadditem')" placeholder="Enter Title" required><span>You have to enter a title</span>
                        </div>									

                    </div>


                    <div class="row">									
                        <div class="col-sm-6">				
                            <label for="quantityadditem">Quantity</label>
                            <input type="number" class="form-control" id="quantityadditem" name="quantityaddshop" oninput="style_inp('quantityadditem')" placeholder="Quantity of Item" min="1" required><span>Invalid Quantity input</span>
                        </div>
                        <div class="col-sm-6">				
                            <label for="priceadditem">Price</label>
                            <input type="number" class="form-control" id="priceadditem" name="priceaddshop" oninput="style_inp('priceadditem')"  placeholder="1 Quantity Price" min="0" step="0.01" required><span>Invalid Price input</span>
                        </div>
                        							
                    </div>
                    <div class="row">
                        <div class="col-sm-6">				

                            <label>Assigned To</label>		
                            <select class="form-control" id="assignedToShop" onfocus="antegeia()" multiple>
                                <c:forEach items="${userslist}" var="userslist">

                                    <option>${userslist.username}</option>

                                </c:forEach>

                            </select>

                        </div>	
                        <div class="col-sm-6">
                            <label for="checkaddpending">Status</label>
                            <div class="radio" id="checkaddpending">
                                <label>
                                    <input name="statusradio" type="radio" value="Pending">
                                    Pending
                                </label>
                            </div>
                            <div class="radio" id="checkaddcomplete">
                                <label>
                                    <input name="statusradio" type="radio" value="Completed">
                                    Completed
                                </label>
                            </div>

                        </div>
                    </div>

                </div>
                <div class="modal-footer">
                    <div id="suc_todo_mes_valid_S" style="font-size:120%"></div>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary" data-dismiss="modal" id="addshopbutton" form="addshopitemform" onclick="insertShoppitem()">Add</button>
                </div>











            </form>	

        </div>
    </div>



</div>


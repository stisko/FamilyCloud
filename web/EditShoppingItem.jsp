<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<form id="editask" name="editaskform">
    <div class="modal-body" id="editShop_modal_body">


        <div class="row">									
            <div class="col-sm-6">				
                <label for="titleedititem">Item Title</label>
                <input type="text" class="form-control" id="titleedititem" name="titleeditshop" placeholder="Enter Title" value="${json.selectedShopItem.shoptitle}" required><span>You have to enter a title</span>
            </div>
        </div>




        <div class="row">									
            <div class="col-sm-6">				
                <label for="quantityedititem">Quantity</label>
                <input type="number" class="form-control" id="quantityedititem" name="quantityeditshop" placeholder="Quantity of Item" value="${json.selectedShopItem.quantity}" required min="1"><span>Invalid Quantity input</span>
            </div>
            <div class="col-sm-6">				
                <label for="priceedititem">Price</label>
                <input type="number" step="0.01" class="form-control" id="priceedititem" name="priceeditshop" placeholder="1 Quantity Price" value="${json.selectedShopItem.price}" required min="0"><span>Invalid Price input</span>
            </div>

        </div>


        <div class="row">

            <div class="col-sm-6">				

                <label>Assigned To</label>		
                <select class="form-control" id="assignedToShopedit" onfocus="antegeia()" multiple>
                    <c:forEach items="${userslist}"  var="insta"> 
                        <c:set var="errors" value="false" />
                        <c:forEach items="${seluserlist}"  var="se">


                            <c:if test="${se eq insta.username}">


                                <c:set var="errors" value="true" />

                            </c:if>



                        </c:forEach> 

                        <c:if test="${errors}" >

                            <option selected><c:out value="${insta.username}"></c:out> </option>

                        </c:if>

                        <c:if test="${!errors}" >

                            <option ><c:out value="${insta.username}"></c:out> </option>

                        </c:if>




                    </c:forEach>




                </select>

            </div>

            <div class="col-sm-6">

                <div class="radio">
                    <label>
                        <input name="statusradio2" type="radio" value="Pending" ${json.pending} required>
                        Pending
                    </label>
                </div>
                <div class="radio">
                    <label>
                        <input name="statusradio2" type="radio" value="Completed"${json.completed}>
                        Complete
                    </label>
                </div>


            </div>
        </div>
    </div>

    <div class="modal-footer">
        <div id="suc_todo_mes_valid_Sed" style=" width:70%"></div>
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" id="editshopbutton" data-dismiss="modal" value="${json.selectedShopItem.itemID}" onclick="Save_changes_shop(this.value)">Save</button>
    </div>





</form>
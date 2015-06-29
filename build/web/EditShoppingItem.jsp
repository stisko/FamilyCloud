<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="row">									
    <div class="col-sm-6">				
        <label for="titleedititem">Item Title</label>
        <input type="text" class="form-control" id="titleedititem" placeholder="Enter Title" value="${json.selectedShopItem.shoptitle}">										
    </div>									

</div>




<div class="row">									
    <div class="col-sm-4">				
        <label for="quantityedititem">Quantity</label>
        <input type="text" class="form-control" id="quantityedititem" placeholder="Quantity of Item" value="${json.selectedShopItem.quantity}">										
    </div>
    <div class="col-sm-4">				
        <label for="priceedititem">Price</label>
        <input type="text" class="form-control" id="priceedititem" placeholder="1 Quantity Price" value="${json.selectedShopItem.price}">										
    </div>
    <div class="col-sm-4">				

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
</div>


<div class="row">

    <div class="col-sm-6">

        <div class="radio">
            <label>
                <input name="statusradio2" type="radio" value="Pending" ${json.pending}>
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

<div class="row">
    <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" data-dismiss="modal" value="${json.selectedShopItem.itemID}" onclick="Save_changes_shop(this.value)">Save</button>
    </div>
</div>
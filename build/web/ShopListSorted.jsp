
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:forEach items="${SortedfamSHOP}" var="instaa" varStatus="loopStatus">
                                    <tr>
                                        <td>${loopStatus.index+1}</td>
                                        <td>${instaa.shoptitle}</td>
                                        <td>${instaa.assigned_to}</td>
                                        <td>${instaa.created_by}</td>
                                        <td>${instaa.quantity}</td>
                                        <td>${instaa.price}</td>                                    
                                        <td>${instaa.shopstatus}</td>
                                        
                                                        <c:choose>
                                            <c:when test="${(instaa.created_by eq cuRRENTuserDr.username)||(cuRRENTuserDr.director eq 'Y')}">
                                                <td><button class="btn btn-default"  data-toggle="modal" data-target="#edititem" value="${instaa.shopitemID}" onclick="editShopItem(this.value)"><span class="glyphicon glyphicon-pencil">Edit</span></button></td>
                                        <td><button class="btn btn-default"  data-toggle="modal" data-target="#deletemodal" value="${instaa.shopitemID}" onclick="deleteShopItem(this.value)"><span class="glyphicon glyphicon-remove">Delete</span></button></td>
                                            </c:when>
                                            
                                            <c:otherwise>
                                                <td><button  class="btn btn-default disabled"  data-toggle="modal" data-target="#edititem" value="${instaa.shopitemID}" onclick="editShopItem(this.value)"><span class="glyphicon glyphicon-pencil">Edit</span></button></td>
                                                <td><button  class="btn btn-default disabled"  data-toggle="modal" data-target="#deletemodal" value="${instaa.shopitemID}" onclick="deleteShopItem(this.value)"><span class="glyphicon glyphicon-remove">Delete</span></button></td>
                                            </c:otherwise>
                                        </c:choose>                   
                                        
                                    </tr>

                                </c:forEach>



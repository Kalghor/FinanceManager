<%@include file="header.jsp" %>
<div class="container-fluid p-5" style="width: 60%; margin-top: 120px; margin-right: auto; margin-left: auto">
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">${name}</h6>
        </div>
        <div class="card-body">
                <div id="dataTable_wrapper" class="dataTables_wrapper dt-bootstrap4">
                    <div class="row">
                        <div class="col-sm-12">
                            <c:choose>
                                <c:when test="${!empty rows}">
                                    <table class="table table-bordered dataTable" id="dataTable" role="grid"
                                           aria-describedby="dataTable_info" style="width: 100%;" width="100%"
                                           cellspacing="0">
                                        <thead>
                                        <tr role="row">
                                            <th class="sorting sorting_asc" tabindex="0" aria-controls="dataTable"
                                                rowspan="1" colspan="1" style="width: 272px;">Description
                                            </th>
                                            <th class="sorting" tabindex="0" aria-controls="dataTable" rowspan="1"
                                                colspan="1" style="width: 405px;">Start scheduling date
                                            </th>
                                            <th class="sorting" tabindex="0" aria-controls="dataTable" rowspan="1"
                                                colspan="1" style="width: 190px;">Value
                                            </th>
                                            <th class="sorting" tabindex="0" aria-controls="dataTable" rowspan="1"
                                                colspan="1" style="width: 190px;">Action
                                            </th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="r" items="${rows}">
                                            <tr class="odd">
                                                <td>${r.description}</td>
                                                <td>${r.schedulingDate}</td>
                                                <td>${r.actualValue} zl</td>
                                                <td>
                                                    <div class="btn-group btn-group-sm" role="group"
                                                         aria-label="Basic mixed styles example">
                                                        <a href='<c:url value="/app/scheduledEdit/${r.id}"/>'>
                                                            <button type="button" class="btn btn-warning">Edit</button>
                                                        </a>
                                                        <form action="/app/deleteScheduled" method="POST">
                                                            <input type="hidden" name="id" value="${r.id}"/>
                                                            <input type="hidden" name="categoryName" value="${name}"/>
                                                            <button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#staticBackdrop">
                                                                Delete
                                                            </button>
                                                            <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                                                                <div class="modal-dialog">
                                                                    <div class="modal-content">
                                                                        <div class="modal-header">
                                                                            <h5 class="modal-title" id="staticBackdropLabel">Modal title</h5>
                                                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                                        </div>
                                                                        <div class="modal-body">
                                                                            Are you sure you want to delete the record?
                                                                        </div>
                                                                        <div class="modal-footer">
                                                                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                                                            <button type="submit" name="oldValue" value="${r.actualValue}"
                                                                                    class="btn btn-danger">Delete
                                                                            </button>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </form>
                                                    </div>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                    <div class="row">
                                        <div class="col-xl-10">
                                            <form:form method="get" action="/app/addScheduledExpense">
                                                <button type="submit" class="btn btn-secondary">Add scheduling expense</button>
                                            </form:form>
                                        </div>
                                        <div class="col-xl-2">
                                            <form action="/app/deleteAllScheduled" method="get">
                                                <div class="p-2">
                                                    <input type="hidden" name="categoryName" value="${categoryName}"/>
                                                    <button type="button" class="btn btn-sm btn-danger" data-bs-toggle="modal"
                                                            data-bs-target="#staticBackdrop2">
                                                        Delete All
                                                    </button>
                                                </div>
                                                <div class="modal fade" id="staticBackdrop2" data-bs-backdrop="static"
                                                data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel2"
                                                aria-hidden="true">
                                                    <div class="modal-dialog">
                                                        <div class="modal-content">
                                                            <div class="modal-header">
                                                                <h5 class="modal-title" id="staticBackdropLabel2">Delete all scheduled expenses</h5>
                                                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                            </div>
                                                            <div class="modal-body">
                                                                Do you want to delete all scheduled expenses?
                                                            </div>
                                                            <div class="modal-footer">
                                                                <button type="submit"
                                                                    class="btn btn-sm btn-danger">Delete all scheduled expenses
                                                                </button>
                                                            </div>
                                                        </div>
                                                    </div>
                                                 </div>
                                                </button>
                                            </form>
                                        </div>
                                    </div
                                </c:when>
                                <c:otherwise>
                                <form:form method="get" action="/app/addScheduledExpense">
                                    <div>Scheduled expenses have not been added yet</div>
                                    <button type="submit" value="false" class="btn btn-secondary">Add scheduling expense
                                    </button>
                                </form:form>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </div>
        </div>
    </div>
</div>
<%@include file="footer.jsp" %>
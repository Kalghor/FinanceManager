<%@include file="header.jsp" %>
<div class="container-fluid p-5" style="width: 50%; margin-top: 100px; margin-right: auto; margin-left: auto">
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">${name}</h6>
        </div>
        <div class="card-body">
                <div id="dataTable_wrapper" class="dataTables_wrapper dt-bootstrap4">
                    <div class="row">
                        <div class="col-sm-12">
                            <table class="table table-bordered dataTable" id="dataTable" role="grid"
                                   aria-describedby="dataTable_info" style="width: 100%;" width="100%"
                                   cellspacing="0">
                                <thead>
                                <tr role="row">
                                    <th class="sorting sorting_asc" tabindex="0" aria-controls="dataTable"
                                        rowspan="1" colspan="1" style="width: 272px;">Description
                                    </th>
                                    <th class="sorting" tabindex="0" aria-controls="dataTable" rowspan="1"
                                        colspan="1" style="width: 405px;">Date
                                    </th>
                                    <th class="sorting" tabindex="0" aria-controls="dataTable" rowspan="1"
                                        colspan="1" style="width: 200px;"
                                        aria-label="Office: activate to sort column ascending">Value
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
                                        <td>${r.localDate}</td>
                                        <td>${r.actualValue} zl</td>
                                        <td>
                                            <div class="btn-group btn-group-sm" role="group"
                                                 aria-label="Basic mixed styles example">
                                                <a href='<c:url value="/app/edit/${r.id}"/>'>
                                                    <button type="button" class="btn btn-warning">Edit</button>
                                                </a>
                                                <form action="/app/delete" method="POST">
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
                            <form action="/app/deleteAll" method="POST">
                                <input type="hidden" name="categoryName" value="${categoryName}"/>
                                <button type="button" class="btn btn-sm btn-danger" data-bs-toggle="modal"
                                        data-bs-target="#staticBackdrop2">
                                    Delete All
                                </button>
                                <div class="modal fade" id="staticBackdrop2" data-bs-backdrop="static"
                                     data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel2"
                                     aria-hidden="true">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="staticBackdropLabel2">Delete the entire
                                                    category</h5>
                                                <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                        aria-label="Close"></button>
                                            </div>
                                            <div class="modal-body">
                                                Do you want to add all expenses from category: ${categoryName} to your
                                                account balance?
                                            </div>
                                            <div class="modal-footer">
                                                <button type="submit" name="checkAddingToAccountBalance" value="true"
                                                        class="btn btn-secondary" data-bs-dismiss="modal">Remove with
                                                    adding
                                                </button>
                                                <button type="submit" name="checkAddingToAccountBalance" value="false"
                                                        class="btn btn-sm btn-danger">Delete without adding
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                </button>

                            </form>
                        </div>
                    </div>
                </div>
        </div>
    </div>
</div>
<%@include file="footer.jsp" %>
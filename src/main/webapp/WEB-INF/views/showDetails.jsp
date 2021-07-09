<%@include file="header.jsp" %>
<div class="container-fluid p-5" style="width: 50%; margin: auto">
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">${name}</h6>
        </div>
        <div class="card-body">
            <div class="table-responsive">
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
                                            <div class="btn-group btn-group-sm" role="group" aria-label="Basic mixed styles example">
                                                <a href='<c:url value="/app/edit/${r.id}"/>'><button type="button" class="btn btn-warning">Edit</button></a>
                                                <form action="/app/delete" method="POST">
                                                    <input type="hidden" name="id" value="${r.id}"/>
                                                    <input type="hidden" name="categoryName" value="${name}"/>
                                                    <button type="submit" class="btn btn-danger">Delete</button>
                                                </form>
                                            </div>

                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                            <form action="/app/deleteAll" method="POST">
                                <input type="hidden" name="categoryName" value="${categoryName}"/>
                                <button type="submit" class="btn btn-sm btn-danger">Delete All</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="footer.jsp" %>
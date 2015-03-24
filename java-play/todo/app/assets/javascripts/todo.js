require(["jquery", "bootstrap"], function($, bootstrap) {
    $(function () {
        $('#dialog_confirm_delete').on('show.bs.modal', function (event) {
            var button = $(event.relatedTarget);
            var deleteTarget = button.data('delete');
            var modal = $(this);
            modal.find('.modal-footer button.positive').attr('data-delete', deleteTarget);
        });
        $('#dialog_confirm_delete .modal-footer .positive').on('click', function() {
            $('#dialog_confirm_delete').modal('hide');
            var deleteTarget = $('#dialog_confirm_delete .modal-footer button.positive').attr('data-delete');
            $('#delete' + deleteTarget).submit();
        });

        $('#todolist').find('[data-edit-type]').each(function(index) {
            $(this).on('click', function(e) {
                console.log("Click!");
                var entityId = $(this).data('id');
                var td = $(this);
                var id = $(this).data('edit-type');

                var child;
                var text;
                if ($(td).children().length === 0) {
                    text = $(td).text();
                    $(td).text('');
                    child = $("<input />", {
                        'id': id,
                        'value': text,
                        'class': 'form-control'
                    });
                    child.appendTo($(td));
                } else if ($(td).children().first().is(':visible')) {
                    return;
                } else {
                    $(td).text('');
                    child = $(td).children().first();
                    child.show();
                }
                child.focusout(function() {
                    if (child.val().length === 0) {
                        $(td).text(text);
                        child.hide();
                    } else {
                        var params = {};
                        params.id = entityId;
                        params[id] = child.val();
                        $.ajax({
                            type: 'POST',
                            datatype : "json",
                            contentType: "application/json; charset=utf-8",
                            url: '/todos/' + entityId + '/update',
                            data: JSON.stringify(params),
                            success: function(data) {
                                $(td).text(child.val());
                            },
                            error: function(XMLHttpRequest, textStatus, errorThrown) {
                                $(td).text(text);
                            }
                        });
                    }
                });
                child.focus();
            });
        });
    });
});

$ ->
    token = $("meta[name='_csrf']").attr "content"
    header = $("meta[name='_csrf_header']").attr "content"
    $(document).ajaxSend (e, xhr, options) ->
       xhr.setRequestHeader header, token
    $('#dialog_confirm_delete').on 'show.bs.modal', (event) ->
        button = $(event.relatedTarget)
        deleteTarget = button.data 'delete'
        modal = $(@)
        modal.find('.modal-footer button.positive').attr 'data-delete', deleteTarget
    $('#dialog_confirm_delete .modal-footer .positive').on 'click', ->
        $('#dialog_confirm_delete').modal 'hide'
        deleteTarget = $('#dialog_confirm_delete .modal-footer button.positive').attr 'data-delete'
        $("#delete#{deleteTarget}").submit()
    $('#todolist').find('[data-edit-type]').each (index) ->
        $(@).on 'click', (e) ->
            entityId = $(@).data 'id'
            td = $(@)
            id = $(@).data 'edit-type'

            if $(td).children().length is 0
                text = $(td).text()
                $(td).text('')
                child = $("<input />",
                    'id': id
                    'value': text
                    'class': 'form-control'
                )
                child.appendTo $(td)
            else if $(td).children().first().is ':visible'
                return;
            else
                $(td).text ''
                child = $(td).children().first()
                child.show()
            child.focusout ->
                if child.val().length is 0
                    $(td).text text
                    child.hide()
                else
                    params = {}
                    params['id'] = entityId
                    params[id] = child.val()
                    $.ajax
                        type: 'POST'
                        datatype : "json"
                        contentType: "application/json; charset=utf-8"
                        url: "/todos/#{entityId}/update"
                        data: JSON.stringify params
                        success: (data) -> $(td).text child.val()
                        error: (XMLHttpRequest, textStatus, errorThrown) -> $(td).text text
            child.focus()

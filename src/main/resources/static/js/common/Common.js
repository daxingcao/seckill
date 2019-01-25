let common = {
    add_popover: function (params) {
        if (this.is_empty(params.element)) {
            throw 'the is is undefined';
        }
        if (this.is_empty(params.direction)) {
            throw 'the direction is undefined';
        }
        let timeout = params.timeout;
        if (!this.is_empty(timeout) && !timeout instanceof Number) {
            throw 'the timeout is not Number type';
        }
        let ele = params.element;
        let popover_id = ele[0].id + "_1";
        let top = ele.offset().top + ele[0].offsetHeight / 2 - 20;
        let left = ele.offset().left + ele[0].offsetWidth;
        let style = `top:${top}px;left:${left}px;`;
        let html = this.html.popover(popover_id, params.direction, params.type, timeout, params.msg, style);
        $(`#${popover_id}`).remove();
        $("body").append(html);
        if (!this.is_empty(timeout)) {
            window.setTimeout("$('#" + popover_id + "').remove()", timeout);
        }
    },
    del_popover: function (ele) {
        let id = ele.parentElement.parentElement.id;
        $(`#${id}`).remove();
    },
    is_empty: function (obj) {
        return typeof obj === 'undefined' || obj === null || obj === '';
    },
    equals: function (obj1, obj2) {
        if (obj1 instanceof String && obj2 instanceof String) {
            return obj1 === obj2;
        }
        if (obj1 instanceof Number && obj2 instanceof Number) {
            return obj1 === obj2;
        }
        return false;
    },
    html: {
        popover: function (id, direction, theme, timeout, msg, style) {
            let son = {
                arrow: function () {
                    return "<div class='arrow'></div>";
                },
                msg: function () {
                    let close = "<a onclick='common.del_popover(this)' class='remove'><i class='glyphicon glyphicon-remove'></i></a>"
                    return `<div class="popover-msg"><span>${msg}</span>${common.is_empty(timeout) ? close : ""}</div>`;
                }
            };
            let body;
            if (common.equals(direction, 'left')) {
                body = son.arrow() + son.msg(timeout);
            } else {
                body = son.msg(timeout) + son.arrow();
            }
            return `<div id="${id}" style="${style}" class="my-popover ${direction} ${theme}">${body}</div>`;
        }
    }
};
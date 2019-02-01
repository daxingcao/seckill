/** 商品相关页面的js模块代码 */
var shop = {
    // 存放相关url地址
    URL: {
        nowTime: function () {
            return "/seckill/nowTime";
        },
        shopToken: function (shopCode) {
            return "/seckill/" + shopCode + "/getToken";
        },
        seckill: function (shopCode, token) {
            return "/seckill/" + shopCode + "/" + token + "/executeSeckill";
        }
    },
    // 商品详情页js
    detail: {
        init: function (params) {
            var shopCode = params['shopCode'];
            var startTime = new Date(params['startTime']).getTime();
            var endTime = new Date(params['endTime']).getTime();
            var cookieMobile = $.cookie('mobile');
            console.log("shopCode:" + shopCode + ";startTime:" + startTime)
            if (!shop.detail.validMobile(cookieMobile)) {
                $("#myModal").modal({
                    show: true,
                    keyboard: false,
                    backdrop: 'static'
                });
                $("#saveMobile")
                    .click(
                        function () {
                            var mobile = $("input[name=mobile]").val();
                            if (shop.detail.validMobile(mobile)) {
                                $.cookie('mobile', mobile, {
                                    expires: 1,
                                    path: '/seckill'
                                });
                                window.location.reload();
                            } else {
                                $("#errorMsg").hide()
                                    .html('<label class="label label-danger">手机号错误!</label>')
                                    .show(300);
                            }
                        })
            } else {
                // 验证成功
                $.get(shop.URL.nowTime(), {}, function (result) {
                    if (result && result.success) {
                        var nowTime = result.data;
                        shop.detail.countDown(shopCode, nowTime, startTime, endTime);
                    } else {
                        console.log("result:" + result);
                    }
                })
            }
        },
        validMobile: function (mobile) {
            if (mobile && mobile.length == 11 && !isNaN(mobile)) {
                return true;
            } else {
                return false;
            }
        },
        countDown: function (shopCode, nowTime, startTime, endTime) {
            var clock = $("#shopCountDown");
            if (nowTime > endTime) {
                // 编写秒杀结束操作
                clock.html("已结束");
            } else if (nowTime < startTime) {
                // 计时操作
                clock.countdown(new Date(startTime + 1000), function (event) {
                    var format = event.strftime('倒计时:%D天 %H时 %M分 %S秒');
                    clock.html(format);
                }).on('finish.countdown', function () {
                    shop.detail.getToken(shopCode);
                })
            } else {
                shop.detail.getToken(shopCode);
            }
        },
        // 获取商品秒杀的token
        getToken: function (shopCode) {
            $.post(shop.URL.shopToken(shopCode), {}, function (result) {
                if (result && result.success) {
                    var data = result.data;
                    var nowTime = data.nowTime;
                    var startTime = data.startTime;
                    var endTime = data.endTime;
                    if (data.open) {
                        var token = data.token;
                        var ele = $("#shopOperate");
                        ele.hide().html('<button class="btn btn-success" id="seckillShop">抢购</button>');
                        $("#seckillShop").one('click', function () {
                            $(this).addClass('disabled');
                            shop.detail.executeSeckill(shopCode, token, ele);
                        });
                        ele.show();
                    } else {
                        shop.detail.countDown(shopCode, nowTime, startTime,
                            endTime);
                    }
                } else {
                    console.log("result:" + result);
                }
            })
        },
        // 执行秒杀函数
        executeSeckill: function (shopCode, token, element) {
            $.post(shop.URL.seckill(shopCode, token), {}, function (result) {
                if (result && result.success) {
                    element.html('<span class="label label-success">' + result.msg + '</span>');
                } else {
                    element.append('<h4><p style="color:red;">' + result.msg + '</p></h4>');
                    console.log("result:" + result);
                }
            })
        }
    }
}
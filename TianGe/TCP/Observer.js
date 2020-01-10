var Observer = (function() {
	// 防止消息队列暴漏而被篡改故将消息容器作为静态私有变量保存
	var __messages = {};
	return {
		// 注册信息接口
		regist: function(type, fn) {
			if (typeof __messages[type] === 'undefined') {
				__messages[type] = [fn];
			} else {
				__messages[type].push(fn);
			}
		},
		// 发布信息接口
		fire: function(type, args) {
			if (!__messages[type]) return;
			var events = {
					type: type,
					args: args || {}
				},
				i = 0,
				len = __messages[type].length;
			for (; i < len; i++) {
				__messages[type][i].call(this, events);
			}
		},
		// 移出信息接口
		remove: function(type, fn) {
			if (__messages[type] instanceof Array) {
				var i = __messages[type].length - 1;
				for (; i >= 0; i--) {
					__messages[type][i] === fn && __messages[type].splice(i, 1);
				}
			}
		}
	};
})();

Observer.regist('test', function(e) {
	console.log(e.type, e.args.msg);
});

Observer.fire('test', { msg: '传递参数' });



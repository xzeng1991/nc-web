$(function() {
	// init date tables
	var jobTable = $("#job_list").dataTable({
		"deferRender": true,
		"processing" : true, 
	    "serverSide": true,
		"ajax": {
			url: base_url + "/class/pageList",
	        data : function ( d ) {
	        	d.realName = $('#realName').val();
                d.phone = $('#phone').val();
            }
	    },
	    "searching": false,
	    "ordering": false,
	    //"scrollX": true,	// X轴滚动条，取消自适应
	    "columns": [
	                { "data": 'userId',"visible" : false},
	                { "data": 'realName'},
	                { "data": 'phone'},
	                { "data": 'email', "visible" : true},             
	                { "data": '操作' ,
	                	"render": function ( data, type, row ) {
	                		return function(){
	                			var html = '<p userId="'+ row.userId +'" '+
	                							' realName="'+ row.realName +'" '+
	                							' phone="'+ row.phone +'" '+
	                							' email="'+ row.email +'" '+
	                							'>'+
	                							'<button class="btn btn-warning btn-xs update" type="button">编辑</button>  ' +
		                						'<button class="btn btn-danger btn-xs job_operate" type="job_del" type="button">删除</button> ' + 
									'</p>';
	                			
	                			return html;
	                		};
	                	}
	                }
	            ],
		"language" : {
			"sProcessing" : "处理中...",
			"sLengthMenu" : "每页 _MENU_ 条记录",
			"sZeroRecords" : "没有匹配结果",
			"sInfo" : "第 _PAGE_ 页 ( 总共 _PAGES_ 页 )",
			"sInfoEmpty" : "无记录",
			"sInfoFiltered" : "(由 _MAX_ 项结果过滤)",
			"sInfoPostFix" : "",
			"sSearch" : "搜索:",
			"sUrl" : "",
			"sEmptyTable" : "表中数据为空",
			"sLoadingRecords" : "载入中...",
			"sInfoThousands" : ",",
			"oPaginate" : {
				"sFirst" : "首页",
				"sPrevious" : "上页",
				"sNext" : "下页",
				"sLast" : "末页"
			},
			"oAria" : {
				"sSortAscending" : ": 以升序排列此列",
				"sSortDescending" : ": 以降序排列此列"
			}
		}
	});
	
	// 搜索按钮
	$('#searchBtn').on('click', function(){
		jobTable.fnDraw();
	});
	
	// job operate
	$("#job_list").on('click', '.job_operate',function() {
		var typeName;
		var url;
		var type = $(this).attr("type");
		if ("job_pause" == type) {
			typeName = "暂停";
			url = base_url + "/jobinfo/pause";
		} else if ("job_resume" == type) {
			typeName = "恢复";
			url = base_url + "/jobinfo/resume";
		} else if ("job_del" == type) {
			typeName = "删除";
			url = base_url + "/student/remove";
		} else if ("job_trigger" == type) {
			typeName = "执行";
			url = base_url + "/jobinfo/trigger";
		} else {
			return;
		}
		
		var userId = $(this).parent('p').attr("userId");
		var jobName = $(this).parent('p').attr("jobName");
		
		ComConfirm.show("确认" + typeName + "?", function(){
			$.ajax({
				type : 'POST',
				url : url,
				data : {
					"userId" : userId,
					"jobName"  : jobName
				},
				dataType : "json",
				success : function(data){
					if (data.code == 200) {
						ComAlert.show(1, typeName + "成功", function(){
							//window.location.reload();
							jobTable.fnDraw();
						});
					} else {
						ComAlert.show(1, typeName + "失败");
					}
				},
			});
		});
	});
	
	// jquery.validate 自定义校验 “英文字母开头，只含有英文字母、数字和下划线”
	jQuery.validator.addMethod("myValid01", function(value, element) {
		var length = value.length;
		var valid = /^[a-zA-Z][a-zA-Z0-9_]*$/;
		return this.optional(element) || valid.test(value);
	}, "只支持英文字母开头，只含有英文字母、数字和下划线");
	
	// 新增
	$(".add").click(function(){
		$('#addModal').modal({backdrop: false, keyboard: false}).modal('show');
	});
	var addModalValidate = $("#addModal .form").validate({
		errorElement : 'span',  
        errorClass : 'help-block',
        focusInvalid : true,  
        rules : {  
        	jobName : {  
        		required : true ,
                minlength: 1,
                maxlength: 100,
                myValid01:true
            },  
            jobCron : {  
            	required : true ,
                maxlength: 100
            },  
            jobDesc : {  
            	required : true ,
                maxlength: 200
            },
            handler_address : {
            	required : true ,
                maxlength: 200
            },
            handler_name : {
            	required : true ,
                maxlength: 200
            },
            author : {
            	required : true ,
                maxlength: 200
            },
            alarmEmail : {
            	required : true ,
                maxlength: 200
            },
            alarmThreshold : {
            	required : true ,
            	digits:true
            }
        }, 
        messages : {  
        	jobName : {  
        		required :"请输入“任务名”"  ,
                minlength:"“任务名”长度不应低于4位",
                maxlength:"“任务名”长度不应超过100位"
            },  
            jobCron : {
            	required :"请输入“Corn”."  ,
                maxlength:"“Corn”长度不应超过100位"
            },  
            jobDesc : {
            	required :"请输入“任务描述”."  ,
                maxlength:"“任务描述”长度不应超过200位"
            },  
            handler_address : {
            	required :"请输入“执行器地址”."  ,
                maxlength:"“执行器地址”长度不应超过200位"
            },
            handler_name : {
            	required : "请输入“jobHandler”."  ,
                maxlength: "“jobHandler”长度不应超过200位"
            },
            author : {
            	required : "请输入“负责人”."  ,
                maxlength: "“负责人”长度不应超过50位"
            },
            alarmEmail : {
            	required : "请输入“报警邮件”."  ,
                maxlength: "“报警邮件”长度不应超过200位"
            },
            alarmThreshold : {
            	required : "请输入“报警阈值”."  ,
            	digits:"阀值应该为整数."
            }
        }, 
		highlight : function(element) {  
            $(element).closest('.form-group').addClass('has-error');  
        },
        success : function(label) {  
            label.closest('.form-group').removeClass('has-error');  
            label.remove();  
        },
        errorPlacement : function(error, element) {  
            element.parent('div').append(error);  
        },
        submitHandler : function(form) {
        	$.post(base_url + "/student/add",  $("#addModal .form").serialize(), function(data, status) {
    			if (data.code == "200") {
    				ComAlert.show(1, "新增任务成功", function(){
    					window.location.reload();
    				});
    			} else {
    				if (data.msg) {
    					ComAlert.show(2, data.msg);
    				} else {
    					ComAlert.show(2, "新增失败");
    				}
    			}
    		});
		}
	});
	$("#addModal").on('hide.bs.modal', function () {
		$("#addModal .form")[0].reset();
		addModalValidate.resetForm();
		$("#addModal .form .form-group").removeClass("has-error");
		$(".remote_panel").show();	// remote
	});
	
	// GLUE模式开启
	$("#addModal .form .ifGLUE").click(function(){
		var ifGLUE = $(this).is(':checked');
		var $handler_name = $("#addModal .form input[name='handler_name']");
		var $glueSwitch = $("#addModal .form input[name='glueSwitch']");
		if (ifGLUE) {
			$handler_name.val("");
			$handler_name.attr("readonly","readonly");
			$glueSwitch.val(1);
		} else {
			$handler_name.removeAttr("readonly");
			$glueSwitch.val(0);
		}
	});
	$("#updateModal .form .ifGLUE").click(function(){
		var ifGLUE = $(this).is(':checked');
		var $handler_name = $("#updateModal .form input[name='handler_name']");
		var $glueSwitch = $("#updateModal .form input[name='glueSwitch']");
		if (ifGLUE) {
			$handler_name.val("");
			$handler_name.attr("readonly","readonly");
			$glueSwitch.val(1);
		} else {
			$handler_name.removeAttr("readonly");
			$glueSwitch.val(0);
		}
	});
	
	// 更新
	$("#job_list").on('click', '.update',function() {
		$("#updateModal .form input[name='userId']").val($(this).parent('p').attr("userId"));
		$("#updateModal .form input[name='realName']").val($(this).parent('p').attr("realName"));
		$("#updateModal .form input[name='phone']").val($(this).parent('p').attr("phone"));
		$("#updateModal .form input[name='email']").val($(this).parent('p').attr("email"));
		
		// GLUE check
		var $glueSwitch = $("#updateModal .form input[name='glueSwitch']");
		var $handler_name = $("#updateModal .form input[name='handler_name']");
		if ($glueSwitch.val() != 0) {
			$handler_name.attr("readonly","readonly");
			$("#updateModal .form .ifGLUE").attr("checked", true);
		} else {
			$handler_name.removeAttr("readonly");
			$("#updateModal .form .ifGLUE").attr("checked", false);
		}
		
		$('#updateModal').modal({backdrop: false, keyboard: false}).modal('show');
	});
	var updateModalValidate = $("#updateModal .form").validate({
		errorElement : 'span',  
        errorClass : 'help-block',
        focusInvalid : true,  
        rules : {  
            jobCron : {  
            	required : true ,
                maxlength: 100
            },  
            jobDesc : {  
            	required : true ,
                maxlength: 200
            },
            handler_address : {
            	required : true ,
                maxlength: 200
            },
            handler_name : {
            	required : true ,
                maxlength: 200
            },
            author : {
            	required : true ,
                maxlength: 200
            },
            alarmEmail : {
            	required : true ,
                maxlength: 200
            },
            alarmThreshold : {
            	required : true ,
            	digits:true
            }
        }, 
        messages : {  
            jobCron : {
            	required :"请输入“Corn”."  ,
                maxlength:"“Corn”长度不应超过100位"
            },  
            jobDesc : {
            	required :"请输入“任务描述”."  ,
                maxlength:"“任务描述”长度不应超过200位"
            },  
            handler_address : {
            	required :"请输入“执行器地址”."  ,
                maxlength:"“执行器地址”长度不应超过200位"
            },
            handler_name : {
            	required : "请输入“jobHandler”."  ,
                maxlength: "“jobHandler”长度不应超过200位"
            },
            author : {
            	required : "请输入“负责人”."  ,
                maxlength: "“负责人”长度不应超过50位"
            },
            alarmEmail : {
            	required : "请输入“报警邮件”."  ,
                maxlength: "“报警邮件”长度不应超过200位"
            },
            alarmThreshold : {
            	required : "请输入“报警阈值”."  ,
            	digits:"阀值应该为整数."
            }
        }, 
		highlight : function(element) {  
            $(element).closest('.form-group').addClass('has-error');  
        },
        success : function(label) {  
            label.closest('.form-group').removeClass('has-error');  
            label.remove();  
        },
        errorPlacement : function(error, element) {  
            element.parent('div').append(error);  
        },
        submitHandler : function(form) {
    		$.post(base_url + "/student/update", $("#updateModal .form").serialize(), function(data, status) {
    			if (data.code == "200") {
    				ComAlert.show(1, "更新成功", function(){
    					window.location.reload();
    				});
    			} else {
    				if (data.msg) {
    					ComAlert.show(2, data.msg);
					} else {
						ComAlert.show(2, "更新失败");
					}
    			}
    		});
		}
	});
	$("#updateModal").on('hide.bs.modal', function () {
		$("#updateModal .form")[0].reset()
	});
	
	
	/*
	// 新增-添加参数
	$("#addModal .addParam").on('click', function () {
		var html = '<div class="form-group newParam">'+
				'<label for="lastname" class="col-sm-2 control-label">参数&nbsp;<button class="btn btn-danger btn-xs removeParam" type="button">移除</button></label>'+
				'<div class="col-sm-4"><input type="text" class="form-control" name="key" placeholder="请输入参数key[将会强转为String]" maxlength="200" /></div>'+
				'<div class="col-sm-6"><input type="text" class="form-control" name="value" placeholder="请输入参数value[将会强转为String]" maxlength="200" /></div>'+
			'</div>';
		$(this).parents('.form-group').parent().append(html);
		
		$("#addModal .removeParam").on('click', function () {
			$(this).parents('.form-group').remove();
		});
	});
	*/
});

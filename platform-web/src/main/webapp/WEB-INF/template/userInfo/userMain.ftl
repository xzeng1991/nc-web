<!DOCTYPE html>
<html>
<head>
  	<title>星梦年华</title>
  	<#import "/common/common.macro.ftl" as netCommon>
	<@netCommon.commonStyle />
	<!-- DataTables -->
  	<link rel="stylesheet" href="${request.contextPath}/static/adminlte/plugins/datatables/dataTables.bootstrap.css">
  
</head>
<body class="hold-transition skin-blue sidebar-mini <#if cookieMap?exists && "off" == cookieMap["adminlte_settings"].value >sidebar-collapse</#if>">
<div class="wrapper">
	<!-- header -->
	<@netCommon.commonHeader />
	<!-- left -->
	<@netCommon.commonLeft />
	
	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>星梦后台<small>用户管理</small></h1>
		</section>
		
		<!-- Main content -->
	    <section class="content">
	    	<select class="form-control" id="locked" style="display:none;">
		    	<#list userStatusList as item>
		        	<option value="${item.status}" >${item.desc}</option>
		        </#list>
		    </select>
	    	<div class="row">
	    		<div class="col-xs-4">
	              	<div class="input-group">
	                	<span class="input-group-addon">用户类型</span>
                		<select class="form-control" id="userType">
                			<#list userTypeList as item>
                				<option value="${item.type}" >${item.desc}</option>
                			</#list>
	                  	</select>
	              	</div>
	            </div>
	            <div class="col-xs-4">
	              	<div class="input-group">
	                	<span class="input-group-addon">用户名</span>
	                	<input type="text" class="form-control" id="userName" value="${userName}" autocomplete="on" >
	              	</div>
	            </div>
	            <div class="col-xs-2">
	            	<button class="btn btn-block btn-info" id="searchBtn">搜索</button>
	            </div>
	            <div class="col-xs-2">
	            	<button class="btn btn-block btn-success add" type="button">+新增用户</button>
	            </div>
          	</div>
	    	
			<div class="row">
				<div class="col-xs-12">
					<div class="box">
			            <div class="box-header">
			            	<h3 class="box-title">用户列表</h3>
			            </div>
			            <div class="box-body">
			              	<table id="job_list" class="table table-bordered table-striped">
				                <thead>
					            	<tr>
					            		<th name="userId" >userId</th>
					                	<th name="userName" >用户名</th>
					                	<th name="password" >密码</th>
					                  	<th name="userType" >用户类型</th>
					                  	<th name="locked" >状态</th>
					                  	<th name="credit" >积分</th>
					                  	<th name="createTime" >创建时间</th>
					                  	<th name="createUser" >创建人</th>
					                  	<th name="modifyTime" >修改时间</th>
					                  	<th name="modifyUser" >修改人</th>
					                  	<th>操作</th>
					                </tr>
				                </thead>
				                <tbody></tbody>
				                <tfoot></tfoot>
							</table>
						</div>
					</div>
				</div>
			</div>
	    </section>
	</div>
	
	<!-- footer -->
	<@netCommon.commonFooter />
</div>

<!-- job新增.模态框 -->
<div class="modal fade" id="addModal" tabindex="-1" role="dialog"  aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
            	<h4 class="modal-title" >新增用户</h4>
         	</div>
         	<div class="modal-body">
				<form class="form-horizontal form" role="form" >
					<div class="form-group">
						<label for="firstname" class="col-sm-2 control-label">用户类型<font color="red">*</font></label>
						<div class="col-sm-4">
							<select class="form-control" name="userType" >
	                			<#list userTypeList as item>
	                				<option value="${item.type}" >${item.desc}</option>
	                			</#list>
	                  		</select>
						</div>
						<label for="firstname" class="col-sm-2 control-label">用户名<font color="red">*</font></label>
						<div class="col-sm-4"><input type="text" class="form-control" name="userName" placeholder="请输入“用户名”" minlength="4" maxlength="100" ></div>
					</div>
					<div class="form-group">
						<label for="lastname" class="col-sm-2 control-label">密码<font color="red">*</font></label>
						<div class="col-sm-4"><input type="text" class="form-control" name="password" placeholder="请输入“密码”" maxlength="100" ></div>
						<label for="firstname" class="col-sm-2 control-label">状态<font color="red">*</font></label>
						<div class="col-sm-4">
							<select class="form-control" name="locked" >
		            			<#list userStatusList as item>
		            				<option value="${item.status}" >${item.desc}</option>
		            			</#list>
		                  	</select>
						</div>					
					</div>
					<div class="form-group">
						<div class="col-sm-offset-3 col-sm-6">
							<button type="submit" class="btn btn-primary"  >保存</button>
							<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						</div>
					</div>
				</form>
         	</div>
		</div>
	</div>
</div>

<!-- 更新.模态框 -->
<div class="modal fade" id="updateModal" tabindex="-1" role="dialog"  aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
            	<h4 class="modal-title" >更新用户信息</h4>
         	</div>
         	<div class="modal-body">
				<form class="form-horizontal form" role="form" >
					<div class="form-group">
						<input type="hidden" class="form-control" name="userId"  >
					</div>
					<div class="form-group">
						<label for="firstname" class="col-sm-2 control-label">用户类型<font color="red">*</font></label>
						<div class="col-sm-4">
							<select class="form-control" name="userType" >
	                			<#list userTypeList as item>
	                				<option value="${item.type}" >${item.desc}</option>
	                			</#list>
	                  		</select>
						</div>
						<label for="firstname" class="col-sm-2 control-label">用户名称<font color="red">*</font></label>
						<div class="col-sm-4"><input type="text" class="form-control" name="userName" placeholder="请输入“用户名称”" minlength="4" maxlength="100" readonly ></div>
					</div>
					<div class="form-group">
						<label for="lastname" class="col-sm-2 control-label">密码<font color="red">*</font></label>
						<div class="col-sm-4"><input type="text" class="form-control" name="password" placeholder="请输入“密码”" maxlength="100" ></div>
						<label for="lastname" class="col-sm-2 control-label">状态<font color="red">*</font></label>
						<div class="col-sm-4">
							<select class="form-control" name="locked" >
		            			<#list userStatusList as item>
		            				<option value="${item.status}" >${item.desc}</option>
		            			</#list>
		                  	</select>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-3 col-sm-6">
							<button type="submit" class="btn btn-primary"  >保存</button>
							<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						</div>
						<div class="col-sm-3">
							<!--<div class="checkbox">
		                        <label><input type="checkbox" class="ifGLUE" >开启GLUE模式<font color="black">*</font></label>
		                        <input type="hidden" name="glueSwitch" value="0" >
	                    	</div> -->
						</div>
					</div>
				</form>
         	</div>
		</div>
	</div>
</div>

<@netCommon.commonScript />
<@netCommon.comAlert />
<!-- DataTables -->
<script src="${request.contextPath}/static/adminlte/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="${request.contextPath}/static/adminlte/plugins/datatables/dataTables.bootstrap.min.js"></script>
<script src="${request.contextPath}/static/plugins/jquery/jquery.validate.min.js"></script>
<!-- daterangepicker -->
<script src="${request.contextPath}/static/adminlte/plugins/daterangepicker/moment.min.js"></script>
<script src="${request.contextPath}/static/adminlte/plugins/daterangepicker/daterangepicker.js"></script>
<script src="${request.contextPath}/static/js/userInfo.index.1.js"></script>
</body>
</html>

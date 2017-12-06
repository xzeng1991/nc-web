<!DOCTYPE html>
<html>
<head>
  	<title>教学管理中心</title>
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
			<h1>管理中心<small>学员缴费管理</small></h1>
			<!--
			<ol class="breadcrumb">
				<li><a><i class="fa fa-dashboard"></i>学员缴费管理</a></li>
				<li class="active">学员缴费管理</li>
			</ol>
			-->
		</section>
		
		<!-- Main content -->
	    <section class="content">
	    
	    	<div class="row">
	    		<div class="col-xs-4">
	              	<div class="input-group">
	                	<span class="input-group-addon">学员姓名</span>
                		<input type="text" class="form-control" id="realName" value="${realName}" autocomplete="on" >
	              	</div>
	            </div>
	            <div class="col-xs-4">
	              	
	            </div>
	            <div class="col-xs-2">
	            	<button class="btn btn-block btn-info" id="searchBtn">搜索</button>
	            </div>
	            <div class="col-xs-2">
	            	<button class="btn btn-block btn-success add" type="button">+新增学员缴费</button>
	            </div>
          	</div>
	    	
			<div class="row">
				<div class="col-xs-12">
					<div class="box">
			            <div class="box-header">
			            	<h3 class="box-title">学员缴费列表</h3>
			            </div>
			            <div class="box-body">
			              	<table id="job_list" class="table table-bordered table-striped">
				                <thead>
					            	<tr>
					            		<th name="user_id" >学员ID</th>
					                	<th name="realName" >姓名</th>
					                <th name="phone" >手机</th>
					                	<th name="email" >邮箱</th>
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
            	<h4 class="modal-title" >新增学员缴费</h4>
         	</div>
         	<div class="modal-body">
				<form class="form-horizontal form" role="form" >
					<div class="form-group">
						<label for="firstname" class="col-sm-2 control-label">学员姓名<font color="red">*</font></label>
						<div class="col-sm-4"><input type="text" class="form-control" name="realName" placeholder="请输入“姓名”" minlength="4" maxlength="100" ></div>
						<label for="firstname" class="col-sm-2 control-label">手机<font color="red">*</font></label>
						<div class="col-sm-4"><input type="text" class="form-control" name="phone" placeholder="请输入“手机”" minlength="4" maxlength="100" ></div>
					</div>
					<div class="form-group">
						<label for="lastname" class="col-sm-2 control-label">邮箱<font color="red">*</font></label>
						<div class="col-sm-4"><input type="text" class="form-control" name="email" placeholder="请输入“email”" maxlength="100" ></div>
						<label for="lastname" class="col-sm-2 control-label"><font color="red"></font></label>
						<div class="col-sm-4"></div>
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
            	<h4 class="modal-title" >更新学员缴费</h4>
         	</div>
         	<div class="modal-body">
				<form class="form-horizontal form" role="form" >
					<div class="form-group">
						<label for="firstname" class="col-sm-2 control-label">学员ID<font color="red">*</font></label>
						<div class="col-sm-4"><input type="text" class="form-control" name="userId" placeholder="请输入“学员ID”" minlength="4" maxlength="100" readonly ></div>
						<label for="firstname" class="col-sm-2 control-label">学员姓名<font color="red">*</font></label>
						<div class="col-sm-4"><input type="text" class="form-control" name="realName" placeholder="请输入“学员姓名”" minlength="4" maxlength="100" readonly ></div>
					</div>
					<div class="form-group">
						<label for="firstname" class="col-sm-2 control-label">手机<font color="red">*</font></label>
						<div class="col-sm-4"><input type="text" class="form-control" name="phone" placeholder="请输入“手机”" minlength="4" maxlength="100" readonly ></div>
						<label for="lastname" class="col-sm-2 control-label">邮箱<font color="red">*</font></label>
						<div class="col-sm-4"><input type="text" class="form-control" name="email" placeholder="请输入“email”" maxlength="100" ></div>
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

<@netCommon.commonScript />
<@netCommon.comAlert />
<!-- DataTables -->
<script src="${request.contextPath}/static/adminlte/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="${request.contextPath}/static/adminlte/plugins/datatables/dataTables.bootstrap.min.js"></script>
<script src="${request.contextPath}/static/plugins/jquery/jquery.validate.min.js"></script>
<!-- daterangepicker -->
<script src="${request.contextPath}/static/adminlte/plugins/daterangepicker/moment.min.js"></script>
<script src="${request.contextPath}/static/adminlte/plugins/daterangepicker/daterangepicker.js"></script>
<script src="${request.contextPath}/static/js/student.index.1.js"></script>
</body>
</html>

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
			<h1>星梦后台<small>星座管理</small></h1>
			<!--
			<ol class="breadcrumb">
				<li><a><i class="fa fa-dashboard"></i>微信后台</a></li>
				<li class="active">星座管理</li>
			</ol>
			-->
		</section>
		
		<!-- Main content -->
	    <section class="content">
	    
	    	<div class="row">
	    		<!--<div class="col-xs-4">
	              	<div class="input-group">
	                	<span class="input-group-addon">任务组</span>
                		<select class="form-control" id="jobGroup" >
                			<#list jobGroupList as group>
                				<option value="${group}" >${group.desc}</option>
                			</#list>
	                  	</select>
	              	</div>
	            </div>-->
	            <div class="col-xs-4">
	              	<div class="input-group">
	                	<span class="input-group-addon">星座名</span>
	                	<input type="text" class="form-control" id="xzName" value="${xzName}" autocomplete="on" >
	              	</div>
	            </div>
	            <div class="col-xs-2">
	            	<button class="btn btn-block btn-info" id="searchBtn">搜索</button>
	            </div>
	            <!--<div class="col-xs-2">
	            	<button class="btn btn-block btn-success add" type="button">+新增任务</button>
	            </div>-->
          	</div>
	    	
			<div class="row">
				<div class="col-xs-12">
					<div class="box">
			            <div class="box-header">
			            	<h3 class="box-title">星座列表</h3>
			            </div>
			            <div class="box-body">
			              	<table id="job_list" class="table table-bordered table-striped">
				                <thead>
					            	<tr>
					            		<th name="id" >id</th>
					                	<th name="xzName" >星座名称</th>
					                  	<th name="xzInfo" >星座信息</th>
					                  	<th name="xzStartTime" >星座开始日期</th>
					                  	<th name="xzEndTime" >星座结束日期</th>
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
            	<h4 class="modal-title" >新增任务调度信息</h4>
         	</div>
         	<div class="modal-body">
				<form class="form-horizontal form" role="form" >
					<div class="form-group">
						<label for="firstname" class="col-sm-2 control-label">任务组<font color="red">*</font></label>
						<div class="col-sm-4">
							<select class="form-control" name="jobGroup" >
		            			<#list jobGroupList as group>
		            				<option value="${group}" >${group.desc}</option>
		            			</#list>
		                  	</select>
						</div>
						<label for="firstname" class="col-sm-2 control-label">任务名<font color="red">*</font></label>
						<div class="col-sm-4"><input type="text" class="form-control" name="jobName" placeholder="请输入“任务名”" minlength="4" maxlength="100" ></div>
					</div>
					<div class="form-group">
						<label for="lastname" class="col-sm-2 control-label">Corn<font color="red">*</font></label>
						<div class="col-sm-4"><input type="text" class="form-control" name="jobCron" placeholder="请输入“Corn”" maxlength="100" ></div>
						<label for="lastname" class="col-sm-2 control-label">描述<font color="red">*</font></label>
						<div class="col-sm-4"><input type="text" class="form-control" name="jobDesc" placeholder="请输入“描述”" maxlength="200" ></div>
					</div>
					<div class="form-group">
						<label for="lastname" class="col-sm-2 control-label">执行器地址<font color="red">*</font></label>
						<div class="col-sm-4"><input type="text" class="form-control" name="handler_address" placeholder="请输入“执行器地址”" maxlength="200" ></div>
						<label for="lastname" class="col-sm-2 control-label">jobHandler<font color="red">*</font></label>
						<div class="col-sm-4"><input type="text" class="form-control" name="handler_name" placeholder="请输入“jobHandler”" maxlength="200" ></div>
					</div>
					<div class="form-group">
						<label for="firstname" class="col-sm-2 control-label">执行参数<font color="black">*</font></label>
						<div class="col-sm-4"><input type="text" class="form-control" name="handler_params" placeholder="请输入“执行参数”" maxlength="100" ></div>
						<label for="lastname" class="col-sm-2 control-label">负责人<font color="red">*</font></label>
						<div class="col-sm-4"><input type="text" class="form-control" name="author" placeholder="请输入“负责人”" maxlength="200" ></div>
					</div>
					<div class="form-group">
						<label for="lastname" class="col-sm-2 control-label">报警邮件<font color="red">*</font></label>
						<div class="col-sm-4"><input type="text" class="form-control" name="alarmEmail" placeholder="请输入“报警邮件”，多个邮件地址逗号分隔" maxlength="200" ></div>
						<label for="lastname" class="col-sm-2 control-label">报警阈值<font color="red">*</font></label>
						<div class="col-sm-4"><input type="text" class="form-control" name="alarmThreshold" placeholder="请输入“报警阈值”" maxlength="200" ></div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-3 col-sm-6">
							<button type="submit" class="btn btn-primary"  >保存</button>
							<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						</div>
						<div class="col-sm-3">
							<div class="checkbox">
		                        <label><input type="checkbox" class="ifGLUE" >开启GLUE模式<font color="black">*</font></label>
		                        <input type="hidden" name="glueSwitch" value="0" >
	                    	</div>
						</div>
					</div>

<input type="hidden" name="glueRemark" value="GLUE代码初始化" >
<textarea name="glueSource" style="display:none;" >
package com.xxl.job.service.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xxl.job.client.handler.IJobHandler;
import com.xxl.job.client.handler.IJobHandler.JobHandleStatus;

public class DemoJobHandler extends IJobHandler {
	private static transient Logger logger = LoggerFactory.getLogger(DemoJobHandler.class);
	
	@Override
	public JobHandleStatus handle(String... params) throws Exception {
		logger.info("XXL-JOB, Hello World.");
		return JobHandleStatus.SUCCESS;
	}
	
}
</textarea>
					
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
            	<h4 class="modal-title" >更新星座信息</h4>
         	</div>
         	<div class="modal-body">
				<form class="form-horizontal form" role="form" >
					<div class="form-group">
						<label for="firstname" class="col-sm-2 control-label">星座名称<font color="red">*</font></label>
						<div class="col-sm-4"><input type="text" class="form-control" name="xzName" placeholder="请输入“星座名称”" minlength="4" maxlength="100" readonly ></div>
						<label for="firstname" class="col-sm-2 control-label">星座信息<font color="red">*</font></label>
						<div class="col-sm-4"><input type="text" class="form-control" name="xzInfo" placeholder="请输入“星座信息”" minlength="4" maxlength="100" readonly ></div>
					</div>
					<div class="form-group">
						<label for="lastname" class="col-sm-2 control-label">开始日期<font color="red">*</font></label>
						<div class="col-sm-4"><input type="text" class="form-control" name="xzStartTime" placeholder="请输入“开始日期”" maxlength="100" ></div>
						<label for="lastname" class="col-sm-2 control-label">结束日期<font color="red">*</font></label>
						<div class="col-sm-4"><input type="text" class="form-control" name="xzEndTime" placeholder="请输入“结束日期”" maxlength="200" ></div>
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
<script src="${request.contextPath}/static/js/xzinfo.index.1.js"></script>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<script src="<s:url value="/assets/js/echarts/echarts.js"/>"></script>

<div class="col-xs-12" id="chart" style="height: 500px;"></div>

<script type="text/javascript">
	// 路径配置
	require.config({
		paths : {
			echarts : '<s:url value="/assets/js/echarts"/>'
		}
	});

	var myChart = null;
	// 使用
	require([ 'echarts', 'echarts/chart/line', 'echarts/chart/bar',
			'echarts/chart/scatter', 'echarts/chart/k', 'echarts/chart/pie',
			'echarts/chart/radar', 'echarts/chart/force',
			'echarts/chart/chord', 'echarts/chart/gauge',
			'echarts/chart/funnel', 'echarts/chart/eventRiver' ], function(ec) {
		// 基于准备好的dom，初始化echarts图表
		myChart = ec.init(document.getElementById('chart'));

		option = {
			title: {
				text: '日志统计报表',
				subtext: '每日GET / POST数'
			},
			tooltip: {
				trigger: 'axis'
			},
			legend: {
				data: ['POST调用次数', 'GET调用次数']
			},
			toolbox: {
				show: true,
				feature: {
					mark: {
						show: true
					},
					magicType: {
						show: true,
						type: ['line', 'bar']
					},
					restore: {
						show: true
					},
					saveAsImage: {
						show: true
					}
				}
			},
			xAxis: [{
				type: 'category',
				data: [${createDates}]
			}],
			yAxis: [{
				type: 'value'
			}],
			series: [{
				name: 'POST调用次数',
				type: 'bar',
				data: [${postCounts}],
				markPoint: {
					data: [{
						type: 'max',
						name: '日最大值'
					}, {
						type: 'min',
						name: '日最小值'
					}]
				},
				markLine: {
					data: [{
						type: 'average',
						name: '平均值'
					}]
				}
			}, {
				name: 'GET调用次数',
				type: 'bar',
				data: [${getCounts}],
				markPoint: {
					data: [{
						type: 'max',
						name: '日最大值'
					}, {
						type: 'min',
						name: '日最小值'
					}]
				},
				markLine: {
					data: [{
						type: 'average',
						name: '日平均值'
					}]
				}
			}]
		};

		// 为echarts对象加载数据 
		myChart.setOption(option);
	});
</script>

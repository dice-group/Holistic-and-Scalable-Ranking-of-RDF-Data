$(document).ready(function() {
	$("#classesSelction").hide();
	$("#resultsRanking").hide();
});

function printclass(classelemenent) {
	var content = "<label> <input type=\"radio\" class=\"option-input radio\" "
			+ "name=\"example\" />" + classelemenent + "</label>"

}

function printClasses(data) {
	$("#classesSelction").show();
	$("#resultsRanking").hide();
	$("#dataset").hide();
	classes = data;
	$("#classList").html('');
	$.each(classes, function(i, v) {
		printclass(v.uri);
	});
}


function getClassList() {
	// get the form data using another method
	var e = document.getElementById("selectedDataset");
	datasetName = e.options[e.selectedIndex].value;
	$.ajax({
		url : "/getClass",// servlet URL that gets
		type : "POST",// request type, can be GET
		data : {
			datasetName : datasetName,// data to be sent
		},
		dataType : "json",// type of data returned
		success : function(data, status, xhr) {
			printClasses(data);
		}
	})
};
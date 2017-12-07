$(document).ready(function() {
	$("#classesSelction").hide();
	$("#resultsRanking").hide();
	$("#datasetList").submit(function(e) {
		e.preventDefault();
	});
});

function printclass(classelemenent) {

}

function printClasses(data) {
	$("#dataset").hide();
	$("#classesSelction").show();
	$("#resultsRanking").hide();

	classes = data;
	var content = "";
	$
			.each(
					classes,
					function(i, v) {
						content += "<label> <input type=\"radio\" class=\"option-input radio\" "
								+ "name=\"example\" /  value= \""
								+ v.uri
								+ "\"  >" + v.uri + "</label> </br>"
					});

	content += "<div class=\"form-group row\">"
			+ "<div class=\"offset-sm-2 col-sm-10\">"
			+ "<button type=\"submit\"class=\"btn btn-outlinesubmit btn-xl js-scroll-trigger\">Submit</button> </div>"
			+ "</div>"

	$("#classList").append($(content));
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
		success : function(data) {
			printClasses(data);
		}
	})
};

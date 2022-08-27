function highlight(){
	$("img").css("transition", "transform 500ms ease-in-out");
		
	$("img").hover(    
		// Handler for mouseenter
		function(){
			$("img").css("transform", "scale(1.2)");
		},
		// Handler for mouseleave
		function(){
			$("img").css("transform", "scale(1)");
		}
	);
}
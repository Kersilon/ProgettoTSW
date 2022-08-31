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

/*
Nice try

function highlight(){
	let id;
	$("img").css("transition", "transform 500ms ease-in-out");
		
	$("img").hover(
		// Handler for mouseenter
		function(){
			id = "#" + $("img").attr("id");
			console.log(id);
			$(id).css("transform", "scale(1.2)");
		},
		// Handler for mouseleave
		function(){
			$(id).css("transform", "scale(1)");
		}
	);
}*/
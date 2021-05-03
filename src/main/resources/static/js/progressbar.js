$(document)
				.ready(
						function() {

							var current_fs, next_fs, previous_fs; // fieldsets
							var opacity;
							var current = 1;
							var steps = $("fieldset").length;

							//setProgressBar(current);

							$(".next")
									.click(
											function() {
												current_fs = $(this).parent();
												next_fs = $(this).parent()
														.next();

												// Add Class Active
												$("#progressbar li").eq(
														$("fieldset").index(
																next_fs))
														.addClass("active");

												var username = document
														.getElementById("input-username").value;
												var result = false;

												var users = '[[${listUsers}]]';
												var format = users.replace(
														/&quot;/g, '"');
												var format2 = format.replace(
														'[', '');
												var format3 = format2.replace(
														']', '');
												var arr = format3.split(",");
												var obj;
												for (var i = 0; i < arr.length; i++) {
													obj = JSON.parse(arr[i])
													if (obj.username === username) {
														result = true;
													}
													//console.log(obj.username);
												}
console.log(result);
												if (!result) {
													// show the next fieldset
													next_fs.show();
													// hide the current fieldset with style
													current_fs
															.animate(
																	{
																		opacity : 0
																	},
																	{
																		step : function(
																				now) {
																			// for making fielset appear animation
																			opacity = 1 - now;

																			current_fs
																					.css({
																						'display' : 'none',
																						'position' : 'relative'
																					});
																			next_fs
																					.css({
																						'opacity' : opacity
																					});
																		},
																		duration : 500
																	});
													setProgressBar(++current);
												} else {
													$('#deleteModalAuth')
															.modal();
												}
											});

							$(".previous").click(
									function() {

										current_fs = $(this).parent();
										previous_fs = $(this).parent().prev();

										// Remove class active
										$("#progressbar li")
												.eq(
														$("fieldset").index(
																current_fs))
												.removeClass("active");

										// show the previous fieldset
										previous_fs.show();

										// hide the current fieldset with style
										current_fs.animate({
											opacity : 0
										}, {
											step : function(now) {
												// for making fielset appear animation
												opacity = 1 - now;

												current_fs.css({
													'display' : 'none',
													'position' : 'relative'
												});
												previous_fs.css({
													'opacity' : opacity
												});
											},
											duration : 500
										});
										setProgressBar(--current);
									});

							function setProgressBar(curStep) {
								var percent = parseFloat(100 / steps) * curStep;
								percent = percent.toFixed();
								$(".progress-bar").css("width", percent + "%")
							}

							//$(".submit").click(function() {
							//	return false;
							//})

						});
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="g16.model.*" import="java.util.List" %> 
<!DOCTYPE html>
<html lang="zxx" class="no-js">

<head>
    <!-- Mobile Specific Meta -->
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Favicon-->
    <link rel="shortcut icon" href="img/fav.png">
    <!-- Author Meta -->
    <meta name="author" content="CodePixar">
    <!-- Meta Description -->
    <meta name="description" content="">
    <!-- Meta Keyword -->
    <meta name="keywords" content="">
    <!-- meta character set -->
    <meta charset="UTF-8">
    <!-- Site Title -->
    <title>Chiquify</title>

    <!--
            CSS
            ============================================= -->
    <link rel="stylesheet" href="css/linearicons.css">
    <link rel="stylesheet" href="css/owl.carousel.css">
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <link rel="stylesheet" href="css/themify-icons.css">
    <link rel="stylesheet" href="css/nice-select.css">
    <link rel="stylesheet" href="css/nouislider.min.css">
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/main.css">
</head>

<body>

    <!-- Start Header Area -->
	<header class="header_area sticky-header">
		<div class="main_menu">
			<nav class="navbar navbar-expand-lg navbar-light main_box">
				<div class="container">
					<!-- Brand and toggle get grouped for better mobile display -->
					<a class="navbar-brand logo_h" href="index.jsp"><img src="img/logo.png" alt=""></a>
					<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
					 aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<!-- Collect the nav links, forms, and other content for toggling -->
					<div class="collapse navbar-collapse offset" id="navbarSupportedContent">
						<ul class="nav navbar-nav menu_nav ml-auto">
							<li class="nav-item"><a class="nav-link" href="index.jsp">Home</a></li>
							<li class="nav-item"><a class="nav-link" href="login.jsp">Login</a></li>
							<%
				                if(session.getAttribute("email") != null)
				                {
				            %>
								<li class="nav-item"><a class="nav-link" href="chat.jsp">Chat</a></li>
				                <li class="nav-item"><a class="nav-link" href="user.jsp">Profile</a></li>
				            <%
				                }
				            %>
							
						</ul>
						<ul class="nav navbar-nav navbar-right">
							<%
				                if(session.getAttribute("cart") != null)
				                {
				            %>
							<li class="nav-item"><a href="cart.jsp" class="cart"><span class="ti-bag"></span></a></li>
							<%
				                }
				            %>
							<li class="nav-item">
								<button class="search"><span class="lnr lnr-magnifier" id="search"></span></button>
							</li>
						</ul>
					</div>
				</div>
			</nav>
		</div>
		<div class="search_input" id="search_input_box">
			<div class="container">
				<form class="d-flex justify-content-between" METHOD=GET ACTION="search">
					<input type="text" class="form-control" id="search_input" name="cadena" placeholder="Search Here">
					<button type="submit" class="btn"></button>
					<span class="lnr lnr-cross" id="close_search" title="Close Search"></span>
				</form>
			</div>
		</div>
	</header>
	<!-- End Header Area -->
	
	<section class="order_details section_gap"></section>

    <!--================Cart Area =================-->
    <section class="cart_area">
        <div class="container">
            <div class="cart_inner">
                <div class="table-responsive">
                    <table class="table">
                        <thead>
                            <tr>
                                <th scope="col">Product</th>
                                <th scope="col">Price</th>
                                <th scope="col">Quantity</th>
                                <th scope="col">Total</th>
                                <th scope="col"></th>
                            </tr>
                        </thead>
                        <tbody>
                        	<% ProductManager pm = new ProductManager(); %>
							<% List<Item> cart = (List<Item>)session.getAttribute("cart"); %>
							<% int subtotal = 0; %>
							<% for(int i = 0; i < cart.size(); i++){ %>
	                        	<!--================ Product Area =================-->
	                            <tr>
	                                <td>
	                                    <div class="media">
	                                        <div class="d-flex">
	                                            <img src= <% out.print("'" + "data:image/jpeg;base64," + cart.get(i).getProduct().getImagen() + "'"); %> style="width: 20%" name="foto" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Imagen'">
	                                        </div>
	                                        <div class="media-body">
	                                            <p> <% out.print(cart.get(i).getProduct().getTitulo()); %> </p>
	                                        </div>
	                                    </div>
	                                </td>
	                                <td>
	                                    <h5> <% out.print(cart.get(i).getProduct().getPrecio()); %> euros</h5>
	                                </td>
	                                <td>
	                                	<form class="row login_form" METHOD=POST ACTION="changeqty">
		                                    <div class="product_count">
		                                    	<input type="hidden" name="IndexCart" value = <% out.print(i); %> readonly>
		                                        <input type="number" min="0" name="qty" id="sst" maxlength="12" value=<% out.print("'" + cart.get(i).getQuantity() + "'"); %> title="Quantity:"
		                                            class="input-text qty">
												<div class="col-md-12 form-group">
													<button type="submit" value="submit" class="primary-btn">Set</button>
							                	</div>
		                                    </div>
	                                    </form>
	                                </td>
	                                <td>
	                                	<% int total = cart.get(i).getProduct().getPrecio() * cart.get(i).getQuantity(); %>
	                                	<% subtotal += total; %>
	                                    <h5> <% out.print(total); %> euros</h5>
	                                </td>
									 <td>
										<form class="row login_form" METHOD=POST ACTION="removefromcart">
											<input type="hidden" name="IndexCart" value = <% out.print(i); %> readonly>
	 										<div class="col-md-12 form-group">
			                                    <button type="submit" value="submit" class="primary-btn">Remove</button>
			                                </div>
		                                </form>
	                                </td>
	                            </tr>
	                            <!--================ End Product Area =================-->
	                        <% } %>
                            
                            <tr>
                                <td>

                                </td>
                                <td>

                                </td>
                                <td>
                                    <h5>Subtotal</h5>
                                </td>
                                <td>
                                    <h5> <% out.print(subtotal); %> euros </h5>
                                </td>
                            </tr>
                            <tr class="out_button_area">
                                <td>

                                </td>
                                <td>

                                </td>
                                <td>

                                </td>

                                <% if(session.getAttribute("email") != null) { %>
	                                <td>
	                                    <div class="checkout_btn_inner d-flex align-items-center">
	                                        <a class="gray_btn" href="index.jsp">Continue Shopping</a>
	                                    </div>
	                                </td>
                                <% }else{ %>
									<p style="color: red; font-weight: 800;">Please login to proceed to checkout</p>
								<% }%>

                                <td>
                                    <div class="checkout_btn_inner d-flex align-items-center">
                                        <a class="gray_btn" href="index.jsp">Continue Shopping</a>
                                        
                                        
                                        <FORM name="nombre" method="POST" action="sendconfirmation">
                                        	<B>&emsp;&emsp;&emsp;Tarjeta de pago</B>: <BR><BR>&emsp;&emsp;&emsp;
                                        	<INPUT type="text" name="tarjeta" size="60" required> <br> <BR>
											<br> <BR> &emsp;&emsp;&emsp;<INPUT class="primary-btn" type="submit" name="ejecutar3"
											value="Proceed to checkout"><BR><BR>
										</FORM>
                                        
                                    
                                    
                                    
                                    
                                    </div>
                                </td>

                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </section>
    <!--================End Cart Area =================-->

    <!-- start footer Area -->
    <footer class="footer-area section_gap">
        <div class="container">
            <div class="row">
                <div class="col-lg-3  col-md-6 col-sm-6">
                    <div class="single-footer-widget">
                        <h6>About Us</h6>
                        <p>
                            Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt
                            ut labore dolore
                            magna aliqua.
                        </p>
                    </div>
                </div>
                <div class="col-lg-4  col-md-6 col-sm-6">
                    <div class="single-footer-widget">
                        <h6>Newsletter</h6>
                        <p>Stay update with our latest</p>
                        <div class="" id="mc_embed_signup">

                            <form target="_blank" novalidate="true" action="https://spondonit.us12.list-manage.com/subscribe/post?u=1462626880ade1ac87bd9c93a&amp;id=92a4423d01"
                                method="get" class="form-inline">

                                <div class="d-flex flex-row">

                                    <input class="form-control" name="EMAIL" placeholder="Enter Email" onfocus="this.placeholder = ''"
                                        onblur="this.placeholder = 'Enter Email '" required="" type="email">


                                    <button class="click-btn btn btn-default"><i class="fa fa-long-arrow-right"
                                            aria-hidden="true"></i></button>
                                    <div style="position: absolute; left: -5000px;">
                                        <input name="b_36c4fd991d266f23781ded980_aefe40901a" tabindex="-1" value=""
                                            type="text">
                                    </div>

                                    <!-- <div class="col-lg-4 col-md-4">
													<button class="bb-btn btn"><span class="lnr lnr-arrow-right"></span></button>
												</div>  -->
                                </div>
                                <div class="info"></div>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3  col-md-6 col-sm-6">
                    <div class="single-footer-widget mail-chimp">
                        <h6 class="mb-20">Instragram Feed</h6>
                        <ul class="instafeed d-flex flex-wrap">
                            <li><img src="img/i1.jpg" alt=""></li>
                            <li><img src="img/i2.jpg" alt=""></li>
                            <li><img src="img/i3.jpg" alt=""></li>
                            <li><img src="img/i4.jpg" alt=""></li>
                            <li><img src="img/i5.jpg" alt=""></li>
                            <li><img src="img/i6.jpg" alt=""></li>
                            <li><img src="img/i7.jpg" alt=""></li>
                            <li><img src="img/i8.jpg" alt=""></li>
                        </ul>
                    </div>
                </div>
                <div class="col-lg-2 col-md-6 col-sm-6">
                    <div class="single-footer-widget">
                        <h6>Follow Us</h6>
                        <p>Let us be social</p>
                        <div class="footer-social d-flex align-items-center">
                            <a href="#"><i class="fa fa-facebook"></i></a>
                            <a href="#"><i class="fa fa-twitter"></i></a>
                            <a href="#"><i class="fa fa-dribbble"></i></a>
                            <a href="#"><i class="fa fa-behance"></i></a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="footer-bottom d-flex justify-content-center align-items-center flex-wrap">
                <p class="footer-text m-0"><!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class="fa fa-heart-o" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">Colorlib</a>  Downloaded from <a href="https://themeslab.org/" target="_blank">Themeslab</a>
<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
</p>
            </div>
        </div>
    </footer>
    <!-- End footer Area -->

    <script src="js/vendor/jquery-2.2.4.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4"
	 crossorigin="anonymous"></script>
	<script src="js/vendor/bootstrap.min.js"></script>
	<script src="js/jquery.ajaxchimp.min.js"></script>
	<script src="js/jquery.nice-select.min.js"></script>
	<script src="js/jquery.sticky.js"></script>
    <script src="js/nouislider.min.js"></script>
	<script src="js/jquery.magnific-popup.min.js"></script>
	<script src="js/owl.carousel.min.js"></script>
	<!--gmaps Js-->
	<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCjCGmQ0Uq4exrzdcL6rvxywDDOvfAu6eE"></script>
	<script src="js/gmaps.min.js"></script>
	<script src="js/main.js"></script>
</body>

</html>

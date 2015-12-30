<!DOCTYPE html>
<html>
<body>

<h1>With "use strict":</h1>
<h3>Using a variable without declaring it, is not allowed.</h3>

<p>Activate debugging in your browser (F12) to see the error report.</p>

<script>
"use strict";
alert('vv');

try {
x = 3.14;    // This will cause an error (x is not defined).
alert(x);
} catch(ex)
{
	alert(x);
}
</script>

</body>
</html>
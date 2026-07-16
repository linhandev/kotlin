// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: overload-resolution, building-the-overload-candidate-set, call-with-an-explicit-receiver -> paragraph 1 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: member extension from implicit dispatch receiver resolves on explicit extension receiver
 */

interface Y11202

class X11202 : Y11202 {
    fun Y11202.tag11202(): String = "OK"

    fun bar(): String {
        val receiver: Y11202 = this
        return receiver.tag11202()
    }
}

// TESTCASE NUMBER: 1
fun box(): String {
    val x = X11202()
    val y: Y11202 = x
    val fromBar = x.bar()
    val fromWith = with(x) { y.tag11202() }
    return if (fromBar == "OK" && fromWith == "OK") "OK" else "NOK: $fromBar/$fromWith"
}

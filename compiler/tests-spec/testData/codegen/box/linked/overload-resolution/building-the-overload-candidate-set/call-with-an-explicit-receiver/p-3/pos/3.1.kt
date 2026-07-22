/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: overload-resolution, building-the-overload-candidate-set, call-with-an-explicit-receiver -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: call with explicit super-form receiver super.f() resolves supertype member
 */

open class Base11203S {
    open fun tag11203S(): String = "OK"
}

class Leaf11203S : Base11203S() {
    fun read11203S(): String = super.tag11203S()
}

// TESTCASE NUMBER: 1
fun box(): String = if (Leaf11203S().read11203S() == "OK") "OK" else "NOK"

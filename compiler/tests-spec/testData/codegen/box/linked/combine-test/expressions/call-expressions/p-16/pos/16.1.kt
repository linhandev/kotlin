// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-expressions -> paragraph 16 -> sentence 16
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 16 -> sentence 16
 *                overload-resolution, building-the-overload-candidate-set, call-with-named-parameters -> paragraph 16 -> sentence 16
 * NUMBER: 1
 * DESCRIPTION: secondary constructor forwarding to primary constructor preserves default parameter values
 */

// TESTCASE NUMBER: 1
class C(val x: Int = 0) {
    constructor(s: String) : this(s.toInt())
}

fun box(): String {
    if (C("1").x != 1) return "NOK"
    return "OK"
}

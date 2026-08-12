// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 403 -> sentence 403
 * declarations, declaration-visibility -> paragraph 403 -> sentence 403
 * declarations, function-declaration -> paragraph 403 -> sentence 403
 * declarations, function-declaration -> paragraph 403 -> sentence 403
 * NUMBER: 1
 * DESCRIPTION: local function and private member function have different scopes
 */

// TESTCASE NUMBER: 1
class C {
    private fun member(): Int = 2
    fun outer(): Int {
        fun local(): Int = 1
        return local()
    }
}

// TESTCASE NUMBER: 1
fun test(): Int = C().outer()

fun box(): String {
    if (test() != 1) return "NOK"
    return "OK"
}

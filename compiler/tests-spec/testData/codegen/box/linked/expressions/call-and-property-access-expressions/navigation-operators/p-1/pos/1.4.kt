// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, call-and-property-access-expressions, navigation-operators -> paragraph 1 -> sentence 1
 * NUMBER: 4
 * DESCRIPTION: nullApi?.size() yields null for null receiver; api?.size() invokes size on non-null Api?
 */

// TESTCASE NUMBER: 1

class Api {
    fun size(): Int = 2
}

fun box(): String {
    val nullApi: Api? = null
    if (nullApi?.size() != null) return "NOK"
    val api: Api? = Api()
    if (api?.size() != 2) return "NOK"
    return "OK"
}

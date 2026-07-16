/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: control--and-data-flow-analysis, control-flow-graph, expressions -> paragraph 5 -> sentence 5
 * NUMBER: 2
 * DESCRIPTION: safe call evaluates member only when receiver is non-null
 */

class Holder1215 {
    var getterCalled = false
    val value: String
        get() {
            getterCalled = true
            return "OK"
        }
}

// TESTCASE NUMBER: 1
fun box(): String {
    val present: Holder1215? = Holder1215()
    if (present?.value != "OK") return "NOK1"
    if (!present.getterCalled) return "NOK2"

    val absent: Holder1215? = null
    if (absent?.value != null) return "NOK3"
    return "OK"
}

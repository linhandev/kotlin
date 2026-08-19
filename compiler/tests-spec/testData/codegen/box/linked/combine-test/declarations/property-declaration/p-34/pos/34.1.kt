/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, property-declaration -> paragraph 34 -> sentence 34
 * PRIMARY LINKS: declarations, property-declaration, getters-and-setters -> paragraph 34 -> sentence 34
 * NUMBER: 1
 * DESCRIPTION: custom getter without backing field is evaluated on each access
 */

// TESTCASE NUMBER: 1
class Box(var name: String) {
    var reads = 0
    val length: Int
        get() {
            reads++
            return name.length
        }
}

fun test(): Int {
    val b = Box("hello")
    val a = b.length
    val c = b.length
    if (a != 5 || c != 5) return -1
    return b.reads
}

fun box(): String {
    if (test() != 2) return "NOK"
    return "OK"
}

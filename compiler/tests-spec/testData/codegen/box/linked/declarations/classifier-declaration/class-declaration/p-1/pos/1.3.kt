// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, class-declaration -> paragraph 1 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: companion object members are accessible at runtime
 */

// TESTCASE NUMBER: 1
class Holder {
    companion object {
        const val MARKER = "OK"
    }
}

fun box(): String {
    return if (Holder.MARKER == "OK") "OK" else "NOK"
}

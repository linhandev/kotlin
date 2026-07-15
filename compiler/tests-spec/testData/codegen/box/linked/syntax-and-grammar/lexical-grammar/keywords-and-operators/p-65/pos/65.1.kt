// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 65 -> sentence 65
 * NUMBER: 1
 * DESCRIPTION: PACKAGE token in top-level package declaration with qualified name; declared package matches runtime
 */

package test.spec.keyword.p65

class PackageMarker65

// TESTCASE NUMBER: 1
fun box(): String {
    if (PackageMarker65::class.qualifiedName != "test.spec.keyword.p65.PackageMarker65") return "NOK"
    return "OK"
}

// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 66 -> sentence 66
 * NUMBER: 3
 * DESCRIPTION: IMPORT token in import kotlin.reflect.KClass with runtime check
 */
// TESTCASE NUMBER: 1

import kotlin.reflect.KClass

class ImportTarget66

fun box(): String {
    val k: KClass<ImportTarget66> = ImportTarget66::class
    return if (k.simpleName == "ImportTarget66") "OK" else "NOK"
}

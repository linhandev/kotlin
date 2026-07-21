// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: packages-and-imports, importing -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: importList with multiple importHeader entries is valid
 */

import kotlin.collections.listOf
import kotlin.text.isEmpty

// TESTCASE NUMBER: 1
fun case_1(): Boolean = listOf("a").isEmpty()

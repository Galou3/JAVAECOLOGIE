package org.forest.domain.service;

import org.forest.domain.model.Tree;
import org.forest.domain.model.Species;
import org.forest.domain.model.Exposure;
import org.forest.port.driven.TreeRepositoryPort;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TreeServiceTest {

    @Mock
    private TreeRepositoryPort treeRepository;

    @InjectMocks
    private TreeService treeService = new TreeService(treeRepository);

    @Test
    void shouldGetATree() {
        // GIVEN
        final UUID uuid = UUID.randomUUID();
        final Tree repositoryTree = new Tree(uuid, LocalDate.now(), Species.OAK, Exposure.SHADOW, 40);
        when(treeRepository.findById(uuid)).thenReturn(Optional.of(repositoryTree));

        // WHEN
        Optional<Tree> tree = treeService.get(uuid);

        // THEN
        assertTrue(tree.isPresent());
        assertEquals(uuid, tree.map(Tree::id).get());
    }
}